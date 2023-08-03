package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TripReportService {

    private static final Logger LOGGER = LogManager.getLogger(TripReportService.class.getName());

    public String receiveData(String data) throws Exception {
        return data;
    }
}
