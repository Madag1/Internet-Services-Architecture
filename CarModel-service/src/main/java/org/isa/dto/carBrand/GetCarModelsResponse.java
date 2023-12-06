package org.isa.dto.carBrand;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class GetCarModelsResponse {

    @Singular
    private List<GetCarModelResponse> carModels;

}
