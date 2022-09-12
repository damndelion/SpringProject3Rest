package com.example.FirstRestApp.repositories;

import com.example.FirstRestApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
