package org.isa.dto.carBrand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetCarModelCarModelNameResponse {
    private UUID id;
    private String name;
    private int doors;
    @JsonProperty("carBrand_name")
    private String carBrandName;
}
