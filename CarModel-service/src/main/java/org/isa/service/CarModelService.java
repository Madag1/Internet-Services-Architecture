package org.isa.service;

import org.isa.dto.carBrand.GetCarModelCarModelNameResponse;
import org.isa.dto.carBrand.GetCarModelResponse;
import org.isa.dto.carBrand.GetCarModelsResponse;
import org.isa.dto.carBrand.PutCarModelRequest;

import java.util.UUID;

public interface CarModelService {
    void addCarModel(GetCarModelResponse getCarModelResponse);


    GetCarModelsResponse getAllCarModels();

    boolean deleteCarModel(UUID id);

    GetCarModelResponse updateCarModel(UUID id, PutCarModelRequest putCarModelRequest);

    GetCarModelCarModelNameResponse getCarModelById(UUID uuid);
}
