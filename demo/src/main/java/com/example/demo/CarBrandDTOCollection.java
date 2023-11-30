package com.example.demo;

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
public class CarBrandDTOCollection {
    private UUID id;
    private String brandName;
}
