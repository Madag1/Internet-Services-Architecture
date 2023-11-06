package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarBrandServiceImpl implements CarBrandService{
    private final CarBrandRepository carBrandRepository;

    public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }
    @Override
    public void addCarBrand(CarBrand brand) {
        carBrandRepository.save(brand);
    }

    @Override
    public List<CarBrandDTO> getAllCarBrands() {
        System.out.println(carBrandRepository.findAll());
        return carBrandRepository
                .findAll()
                .stream()
                .map(CarBrandMapper::mapToCarBrandDto)
                .collect(Collectors.toList());
    }
    @Override
    public CarBrand getCarBrandByBrandName(String brandName) {
        return carBrandRepository.getCarBrandByBrandName(brandName);
    }
}
