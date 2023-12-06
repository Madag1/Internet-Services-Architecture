package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.GetCarBrandsResponse;
import org.example.dto.GetCarBrandResponse;
import org.example.dto.PutCarBrandRequest;
import org.example.exception.CarBrandNotFoundException;
import org.example.mapper.CarBrandMapper;
import org.example.model.CarBrand;
import org.example.repository.CarBrandRepository;
import org.example.repository.CarBrandRestRepository;
import org.example.service.CarBrandService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;
    private final CarBrandRestRepository carBrandRestRepository;

    @Override
    public void addCarBrand(GetCarBrandResponse getCarBrandResponse) {
        CarBrand carBrand = CarBrandMapper.mapToCarBrand(getCarBrandResponse);
        carBrandRestRepository.addCarBrand(getCarBrandResponse);
        carBrandRepository.save(carBrand);
    }

    @Override
    public GetCarBrandsResponse getAllCarBrands() {
        return CarBrandMapper.mapToGetCarBrandsResponse(carBrandRepository.findAll());
    }

    @Override
    public GetCarBrandResponse getCarBrandById(UUID id) {
        CarBrand carBrand = carBrandRepository
                .findById(id)
                .orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found: " + id));
        return CarBrandMapper.mapToGetCarBrandResponse(carBrand);
    }

    @Override
    public boolean deleteCarBrand(UUID id) {
        CarBrand carBrand = carBrandRepository.findById(id).orElse(null);
        if (carBrand == null) {
            return false;
        }
        carBrandRestRepository.delete(id);
        carBrandRepository.delete(carBrand);
        return true;
    }

    @Override
    public GetCarBrandResponse updateCarBrand(UUID id, PutCarBrandRequest putCarBrandRequest) {
        CarBrand carBrand = carBrandRepository.findById(id)
                .orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found: " + id));

        carBrand.setName(putCarBrandRequest.getName());
        if (!carBrand.getName().equalsIgnoreCase(putCarBrandRequest.getName())) {
            carBrandRestRepository.updateName(id, putCarBrandRequest);
        }
        carBrand.setEmployees(putCarBrandRequest.getEmployees());

        carBrandRepository.save(carBrand);

        return CarBrandMapper.mapToGetCarBrandResponse(carBrand);
    }

}
