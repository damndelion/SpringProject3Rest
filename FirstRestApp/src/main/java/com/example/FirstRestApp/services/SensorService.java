package com.example.FirstRestApp.services;

import com.example.FirstRestApp.models.Sensor;
import com.example.FirstRestApp.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }
    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepository.findByName(name);
    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
