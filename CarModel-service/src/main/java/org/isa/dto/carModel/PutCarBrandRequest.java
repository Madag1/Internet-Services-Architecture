package org.isa.dto.carModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutCarBrandRequest {
    private String name;
}
