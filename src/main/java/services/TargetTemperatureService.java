package services;

import models.TargetTemperatureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TargetTemperatureRepository;

@Service
public class TargetTemperatureService {
    @Autowired
    private TargetTemperatureRepository targetTemperatureRepository;

    public TargetTemperatureEntity saveTargetTemperature(int targetTemperature) {
        TargetTemperatureEntity targetTemperatureEntity = new TargetTemperatureEntity();
        targetTemperatureEntity.setTemperatureValue(targetTemperature);
        return targetTemperatureRepository.save(targetTemperatureEntity);
    }

    public int getTargetTemperature() {
        TargetTemperatureEntity targetTemperature = targetTemperatureRepository.findById(1).orElse(null);
        if (targetTemperature != null) {
            return targetTemperature.getTemperatureValue();
        } else {
            throw new RuntimeException("No target temperature found in the database.");
        }
    }
}
