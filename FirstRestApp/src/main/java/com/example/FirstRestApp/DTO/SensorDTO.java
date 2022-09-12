package com.example.FirstRestApp.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotNull(message = "name should not be empty")
    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
