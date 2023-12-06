package org.isa.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isa.dto.carBrand.GetCarModelCarModelNameResponse;
import org.isa.dto.carBrand.GetCarModelResponse;
import org.isa.dto.carBrand.GetCarModelsResponse;
import org.isa.dto.carBrand.PutCarModelRequest;
import org.isa.exception.CarModelNotFoundException;
import org.isa.exception.CarBrandNotFoundException;
import org.isa.mapper.CarModelMapper;
import org.isa.model.CarModel;
import org.isa.model.CarBrand;
import org.isa.repository.CarModelRepository;
import org.isa.repository.CarBrandRepository;
import org.isa.service.CarModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;
    private final CarBrandRepository carBrandRepository;

    @Override
    public void addCarModel(GetCarModelResponse getCarModelResponse) {
        CarBrand carBrand = carBrandRepository.findById(getCarModelResponse.getCarBrandId())
                .orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found: " + getCarModelResponse.getCarBrandId()));
        CarModel carModel = CarModelMapper.mapToCarModel(getCarModelResponse, carBrand);
        carModelRepository.save(carModel);
    }

    @Override
    public GetCarModelsResponse getAllCarModels() {
        List<CarModel> carModels = carModelRepository.findAll();
        return CarModelMapper.mapToGetCarModelsResponse(carModels);
    }

    @Override
    public boolean deleteCarModel(UUID id) {
        CarModel carModel = carModelRepository.findById(id).orElse(null);
        if (carModel == null) {
            return false;
        }
        carModelRepository.delete(carModel);
        return true;
    }

    @Override
    public GetCarModelResponse updateCarModel(UUID id, PutCarModelRequest putCarModelRequest) {
        CarModel existingCarModel = carModelRepository.findById(id)
                .orElseThrow(() -> new CarModelNotFoundException("CarModel not found: " + id));
        existingCarModel.setName(putCarModelRequest.getName());
        existingCarModel.setDoors(putCarModelRequest.getDoors());

        carModelRepository.save(existingCarModel);

        return CarModelMapper.mapToCarModelDto(existingCarModel);
    }

    @Override
    public GetCarModelCarModelNameResponse getCarModelById(UUID uuid) {
        CarModel carModel = carModelRepository.findById(uuid)
                .orElseThrow(() -> new CarModelNotFoundException("CarModel not found: " + uuid));
        return CarModelMapper.mapToGetCarModelDto(carModel);
    }
}




