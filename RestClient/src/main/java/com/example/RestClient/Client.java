package com.example.RestClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        String sensorName = "testSensor";

        registerSensor(sensorName);

        Random random = new Random();
        double maxtemp = 30;
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            sendMeasurements(random.nextDouble() * maxtemp, random.nextBoolean(), sensorName);
        }
    }
    public static void registerSensor(String sensorName) {
        String url = "http://localhost:8080/sensors/registration";
        Map<String,Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);
        postRequestRestTemplate(url, jsonData);
    }

    public static void postRequestRestTemplate(String url, Map<String, Object> jsonData) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, httpHeaders);

        try {
            restTemplate.postForObject(url,request,String.class);
            System.out.println("OK");
        } catch (HttpClientErrorException exception) {
            System.out.println("Error");
            System.out.println(exception.getMessage());
        }
    }

    public static void sendMeasurements(double value, boolean raining, String sensorName) {
        String url = "http://localhost:8080/measurements/add";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        postRequestRestTemplate(url, jsonData);
    }
}
