package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "target_temperature")
public class TargetTemperatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "temperature_value", nullable = false)
    private int temperatureValue;

    public TargetTemperatureEntity(int id, int temperatureValue) {
        this.id = id;
        this.temperatureValue = temperatureValue;
    }

    public TargetTemperatureEntity() {
    }

    public int getId() {
        return id;
    }

    public int getTemperatureValue() {
        return temperatureValue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTemperatureValue(int temperatureValue) {
        this.temperatureValue = temperatureValue;
    }
}
