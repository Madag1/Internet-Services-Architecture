package org.isa.mapper;


import org.isa.dto.carModel.GetCarBrandResponse;
import org.isa.model.CarBrand;

public class CarBrandMapper {

    public static CarBrand mapToCarBrand(GetCarBrandResponse getCarBrandResponse) {
        return CarBrand.builder()
                .id(getCarBrandResponse.getId())
                .name(getCarBrandResponse.getName())
                .build();
    }

    public static GetCarBrandResponse mapToGetCarBrandResponse(CarBrand carBrand) {
        return GetCarBrandResponse.builder()
                .id(carBrand.getId())
                .name(carBrand.getName())
                .build();
    }
}
