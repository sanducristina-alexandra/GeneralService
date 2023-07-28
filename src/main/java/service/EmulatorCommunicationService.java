package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmulatorCommunicationService {
    private static final Logger LOGGER = LogManager.getLogger(FileService.class.getName());
    public String receiveData(String data) {
        LOGGER.info("Data was received from the Emulator.");
        return data;
    }
}
