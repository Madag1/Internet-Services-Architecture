package com.example.demo;

public class CarModelMapper {
    public static CarModelDTO mapToCarModelDto(CarModel model) {

        return CarModelDTO.builder()
                .id(model.getId())
                .modelName(model.getModelName())
                .doors(model.getDoors())
                .vMax(model.getVMax())
                .carBrand(model.getCarBrand().getBrandName())
                .build();
    }

    public static CarModel mapToCarModel(CarModelDTO carModelDto, CarBrand brand) {
        return CarModel.builder()
                .id(carModelDto.getId())
                .modelName(carModelDto.getModelName())
                .doors(carModelDto.getDoors())
                .vMax(carModelDto.getVMax())
                .carBrand(brand)
                .build();
    }
}

