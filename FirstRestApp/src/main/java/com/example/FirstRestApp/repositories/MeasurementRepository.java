package com.example.FirstRestApp.repositories;

import com.example.FirstRestApp.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurements, Integer> {

}
