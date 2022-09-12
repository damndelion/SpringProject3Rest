package com.example.FirstRestApp.uitl;

import com.example.FirstRestApp.models.Measurements;
import com.example.FirstRestApp.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasurementsValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public MeasurementsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements = (Measurements) target;

        if(sensorService.getSensorByName(measurements.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "No sensor found by this name");
        }
    }
}
