package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutCarBrandRequest {
    private String name;
    private int employees;
}
