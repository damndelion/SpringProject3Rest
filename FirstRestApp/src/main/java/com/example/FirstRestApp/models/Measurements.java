package com.example.FirstRestApp.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurements {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    @NotNull(message = "temperature should not be null")
    @Max(value = 100, message = "temperature should not be higher than 100")
    @Min(value = -100, message = "temperature should be higher than -100")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "raining should not be null")
    private boolean raining;

    @Column(name = "date_time")
    @NotNull
    private LocalDateTime measurementDateTime;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;



    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getMeasurementDateTime() {
        return measurementDateTime;
    }

    public void setMeasurementDateTime(LocalDateTime measurementDateTime) {
        this.measurementDateTime = measurementDateTime;
    }
}
