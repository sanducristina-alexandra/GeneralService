package repositories;

import models.TargetTemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetTemperatureRepository extends JpaRepository<TargetTemperatureEntity, Integer> {
}
