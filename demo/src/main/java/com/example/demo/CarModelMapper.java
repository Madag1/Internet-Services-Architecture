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

    public static CarModel mapToCarModel(CarModelDTOResponse carModelDto, CarBrand brand) {
        return CarModel.builder()
                .id(carModelDto.getId())
                .modelName(carModelDto.getModelName())
                .doors(carModelDto.getDoors())
                .vMax(carModelDto.getVMax())
                .carBrand(brand)
                .build();
    }

    public static CarModelDTOCollection mapToCarCollection(CarModel carModelCollectionDto) {
        return CarModelDTOCollection.builder()
                .id(carModelCollectionDto.getId())
                .modelName(carModelCollectionDto.getModelName())
                .build();
    }

    public static CarModelDTOResponse mapToCarResponse(CarModel carModel) {
        return CarModelDTOResponse.builder()
                .id(carModel.getId())
                .modelName(carModel.getModelName())
                .doors(carModel.getDoors())
                .vMax(carModel.getVMax())
                .carBrand(carModel.getCarBrand().getBrandName())
                .build();
    }
}

