package org.isa.service.impl;


import lombok.RequiredArgsConstructor;
import org.isa.dto.carModel.GetCarBrandResponse;
import org.isa.dto.carModel.PutCarBrandRequest;
import org.isa.exception.CarBrandNotFoundException;
import org.isa.mapper.CarBrandMapper;
import org.isa.model.CarBrand;
import org.isa.repository.CarBrandRepository;
import org.isa.service.CarBrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;

    @Override
    public void addCarBrand(GetCarBrandResponse getCarBrandResponse) {
        CarBrand carBrand = CarBrandMapper.mapToCarBrand(getCarBrandResponse);
        carBrandRepository.save(carBrand);
    }

    @Override
    public boolean deleteCarBrand(UUID id) {
        CarBrand carBrand = carBrandRepository.findById(id).orElse(null);
        if (carBrand == null) {
            return false;
        }
        carBrandRepository.delete(carBrand);
        return true;
    }

    @Override
    public GetCarBrandResponse updateCarBrand(UUID id, PutCarBrandRequest putCarBrandRequest) {
        CarBrand carBrand = carBrandRepository.findById(id)
                .orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found: " + id));
        carBrand.setName(putCarBrandRequest.getName());

        carBrandRepository.save(carBrand);

        return CarBrandMapper.mapToGetCarBrandResponse(carBrand);
    }

    @Override
    public List<GetCarBrandResponse> getAllCarBrands() {
        return carBrandRepository.findAll().stream().map(CarBrandMapper::mapToGetCarBrandResponse).toList();
    }

    @Override
    public GetCarBrandResponse getCarBrandById(UUID id) {
        return CarBrandMapper.mapToGetCarBrandResponse(carBrandRepository
                .findById(id)
                .orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found: " + id)));
    }
}
