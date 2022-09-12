package com.example.FirstRestApp.controllers;

import com.example.FirstRestApp.DTO.SensorDTO;
import com.example.FirstRestApp.models.Sensor;
import com.example.FirstRestApp.services.SensorService;
import com.example.FirstRestApp.uitl.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.FirstRestApp.uitl.ErrorsUtil.errorToClient;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    private final SensorService sensorService;
    @Autowired
    public SensorController(SensorValidator sensorValidator, SensorService sensorService, ModelMapper modelMapper) {
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensorToAdd = modelMapper.map(sensorDTO, Sensor.class);
        sensorValidator.validate(sensorToAdd,bindingResult);

        if (bindingResult.hasErrors()) {
            errorToClient(bindingResult);
        }
        sensorService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
