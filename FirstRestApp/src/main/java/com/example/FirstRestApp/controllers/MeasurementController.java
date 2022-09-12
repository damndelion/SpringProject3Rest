package com.example.FirstRestApp.controllers;

import com.example.FirstRestApp.DTO.MeasurementDTO;
import com.example.FirstRestApp.models.Measurements;
import com.example.FirstRestApp.services.MeasurementService;
import com.example.FirstRestApp.uitl.MeasurementsValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.example.FirstRestApp.uitl.ErrorsUtil.errorToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementsValidator measurementsValidator;
    private final MeasurementService measurementService;
    @Autowired
    public MeasurementController(ModelMapper modelMapper, MeasurementsValidator measurementsValidator, MeasurementService measurmentService) {
        this.modelMapper = modelMapper;
        this.measurementsValidator = measurementsValidator;
        this.measurementService = measurmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addValues(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                BindingResult bindingResult) {
        Measurements measurementsToAdd = modelMapper.map(measurementDTO, Measurements.class);
        measurementsValidator.validate(measurementsToAdd, bindingResult);

        if(bindingResult.hasErrors()) {
            errorToClient(bindingResult);
        }
        measurementService.save(measurementsToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<Measurements> getAll() {
        return measurementService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurements::isRaining).count();
    }

}
