package com.example.demo;

public class CarBrandMapper {
    public static CarBrand mapToCarBrand(CarBrandDTO carBrandDTO) {
        return CarBrand.builder()
                .id(carBrandDTO.getId())
                .brandName(carBrandDTO.getBrandName())
                .employeeNum(carBrandDTO.getEmployeeNum())
                .models(carBrandDTO.getModels())
                .build();
    }

    public static CarBrandDTO mapToCarBrandDto(CarBrand brand) {
        return CarBrandDTO.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .employeeNum(brand.getEmployeeNum())
                .models(brand.getModels())
                .build();
    }

}
