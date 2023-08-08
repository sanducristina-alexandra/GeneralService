package email;

import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmailSender {

    private static final Logger LOGGER = LogManager.getLogger(EmailSender.class.getName());

    public static void sendEmail(String recipient, String lastCoordinates, String lastCoordinatesDate) {

        Courier.init("pk_prod_X8MHBNWVN7M26TG9GXNBHBV2ZYBY");

        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        Map<String, String> to = new HashMap<>();
        to.put("email", recipient);
        message.setTo(to);
        message.setTemplate("2CZXFR52RE43WMKXNV7JPFE0H68A");

        Map<String, Object> data = new HashMap<>();
        data.put("lastCoordinates", lastCoordinates);
        data.put("lastCoordinatesDate", lastCoordinatesDate);

        message.setData((HashMap<String, Object>) data);

        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            LOGGER.info("SOS Email sent: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}