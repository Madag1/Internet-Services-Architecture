package org.example.mapper;

import org.example.dto.GetCarBrandsResponse;
import org.example.dto.GetCarBrandResponse;
import org.example.model.CarBrand;

import java.util.List;

public class CarBrandMapper {

    public static CarBrand mapToCarBrand(GetCarBrandResponse getCarBrandResponse) {
        return CarBrand.builder()
                .id(getCarBrandResponse.getId())
                .employees(getCarBrandResponse.getEmployees())
                .name(getCarBrandResponse.getName())
                .build();
    }

    public static GetCarBrandResponse mapToGetCarBrandResponse(CarBrand carBrand) {
        return GetCarBrandResponse.builder()
                .id(carBrand.getId())
                .employees(carBrand.getEmployees())
                .name(carBrand.getName())
                .build();
    }

    public static GetCarBrandsResponse mapToGetCarBrandsResponse(List<CarBrand> carBrands) {
        return GetCarBrandsResponse.builder()
                .carBrands(carBrands.stream()
                        .map(CarBrandMapper::mapToGetCarBrandResponse)
                        .toList())
                .build();
    }

}
