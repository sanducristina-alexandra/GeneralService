package GeneralService;

import onlineservices.OnlineService;
import onlineservices.services.WindowControl.WindowControlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.filereaders.CsvFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Configurations {
    private static final String MQTT_BROKER_URL = "tcp://broker.emqx.io:1883";
    private static final String MQTT_CLIENT_ID = "OnlineServicesCommunicationService";
    private static final Logger LOGGER = LogManager.getLogger(Configurations.class.getName());

    @Bean
    public WindowControlService windowControlService() {
        WindowControlService windowControlService = new WindowControlService();
        windowControlService.onCreate();
        return windowControlService;
    }

    public List<String> getActivatedServicesNames() {
        CsvFileReader fileReader = new CsvFileReader();
        return fileReader.readFile(new File(".\\src\\main\\resources\\ActivatedServices.csv"));
    }

    @Bean
    public List<OnlineService> activatedServices() {
        List<OnlineService> activatedServices = new ArrayList<>();
        for (String serviceName : getActivatedServicesNames()) {
            if (serviceName.equals(WindowControlService.class.getSimpleName())) {
                activatedServices.add(windowControlService());
                LOGGER.info(serviceName + " is in the activated services list.");
            } else {
                LOGGER.info(serviceName + " is not in the activated services list.");
            }
        }
        return activatedServices;
    }

    @Bean
    public MqttClient mqttClient() throws Exception {
        return new MqttClient(MQTT_BROKER_URL, MQTT_CLIENT_ID, new MemoryPersistence());
    }

}
