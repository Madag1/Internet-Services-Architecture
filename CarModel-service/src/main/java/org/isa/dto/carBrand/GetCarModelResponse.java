package org.isa.dto.carBrand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetCarModelResponse {
    private UUID id;
    private String name;
    private int doors;
    @JsonProperty("carBrand_id")
    private UUID carBrandId;
}
