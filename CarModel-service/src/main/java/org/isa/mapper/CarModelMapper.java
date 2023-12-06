package org.isa.mapper;

import org.isa.dto.carBrand.GetCarModelCarModelNameResponse;
import org.isa.dto.carBrand.GetCarModelResponse;
import org.isa.dto.carBrand.GetCarModelsResponse;
import org.isa.model.CarModel;
import org.isa.model.CarBrand;

import java.util.List;

public class CarModelMapper {
    public static GetCarModelResponse mapToCarModelDto(CarModel carModel) {

        return GetCarModelResponse.builder()
                .carBrandId(carModel.getCarBrand().getId())
                .id(carModel.getId())
                .name(carModel.getName())
                .doors(carModel.getDoors())
                .build();
    }

    public static GetCarModelCarModelNameResponse mapToGetCarModelDto(CarModel carModel) {
        return GetCarModelCarModelNameResponse.builder()
                .carBrandName(carModel.getCarBrand().getName())
                .id(carModel.getId())
                .name(carModel.getName())
                .doors(carModel.getDoors())
                .build();
    }

    public static CarModel mapToCarModel(GetCarModelResponse getCarModelResponse, CarBrand carBrand) {
        return CarModel.builder()
                .id(getCarModelResponse.getId())
                .carBrand(carBrand)
                .name(getCarModelResponse.getName())
                .doors(getCarModelResponse.getDoors())
                .build();
    }

    public static GetCarModelsResponse mapToGetCarModelsResponse(List<CarModel> carModels) {
        return GetCarModelsResponse.builder()
                .carModels(carModels.stream()
                        .map(CarModelMapper::mapToCarModelDto)
                        .toList())
                .build();
    }
}
