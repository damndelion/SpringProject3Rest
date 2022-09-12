package com.example.FirstRestApp.services;

import com.example.FirstRestApp.models.Measurements;
import com.example.FirstRestApp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }
    public void save(Measurements measurements){
        measurements.setSensor(sensorService.getSensorByName(measurements.getSensor().getName()).get());
        measurements.setMeasurementDateTime(LocalDateTime.now());
        measurementRepository.save(measurements);
    }

    public List<Measurements> findAll() {
        return measurementRepository.findAll();
    }


}
