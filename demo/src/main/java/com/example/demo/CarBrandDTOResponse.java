package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = "models")
@EqualsAndHashCode(exclude = "models")
@Builder
public class CarBrandDTOResponse {
    private UUID id;
    private String brandName;
    private int employeeNum;
    @JsonIgnore
    private List<CarModel> models;
    public List<CarModel> getModels() {
        return models == null ? new ArrayList<>() : models;
    }
}
