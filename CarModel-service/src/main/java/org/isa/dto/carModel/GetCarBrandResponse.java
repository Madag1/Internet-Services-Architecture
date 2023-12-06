package org.isa.dto.carModel;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetCarBrandResponse {
    private UUID id;
    private String name;
}
