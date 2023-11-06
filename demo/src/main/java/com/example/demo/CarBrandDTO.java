package com.example.demo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString//(exclude = "carBrand")
@EqualsAndHashCode
@Builder
public class CarBrandDTO {
    private UUID id;
    private String brandName;
    private int employeeNum;
    private List<CarModel> models;
    public List<CarModel> getModels() {
        return models == null ? new ArrayList<>() : models;
    }
}
