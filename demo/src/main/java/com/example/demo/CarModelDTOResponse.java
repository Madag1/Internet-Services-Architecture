package com.example.demo;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@ToString//(exclude = "carBrand")
@EqualsAndHashCode
@Builder
public class CarModelDTOResponse implements Serializable {
    private UUID id;
    private String modelName;
    private int doors;
    private int vMax;
    private String carBrand;
}
