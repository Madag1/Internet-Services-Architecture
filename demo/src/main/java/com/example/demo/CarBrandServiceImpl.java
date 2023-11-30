package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
    public void addCarBrand(CarBrandDTO brandDTO) {
        CarBrand brand = CarBrandMapper.mapToCarBrand(brandDTO);
        carBrandRepository.save(brand);
    }

    @Override
    public List<CarBrandDTOCollection> getAllCarBrands() {
        System.out.println(carBrandRepository.findAll());
        return carBrandRepository
                .findAll()
                .stream()
                .map(CarBrandMapper::mapToCarBrandCollection)
                .collect(Collectors.toList());
    }
    @Override
    public CarBrand getCarBrandByBrandName(String brandName) {
        return carBrandRepository.getCarBrandByBrandName(brandName);
    }

    @Override
    public CarBrandDTOResponse getCarBrandByBrandNames(String brandName) {
        CarBrand brand = carBrandRepository.getCarBrandByBrandName(brandName);
        if (brand == null) {
            throw new CarBrandException("Brand not found: " + brandName);
        }
        return CarBrandMapper.mapToCarBrandResponse(brand);
    }

    @Override
    public boolean deleteCarBrand(UUID id) {
        CarBrand brand = carBrandRepository.findById(id).orElse(null);
        if (brand == null) {
            return false;
        }
        carBrandRepository.delete(brand);
        return true;
    }

    @Override
    public CarBrandDTOResponse updateCarBrand(UUID id, CarBrandDTOResponse updatedCarBrandDto) {
        CarBrand brand = carBrandRepository.findById(id)
                .orElseThrow(() -> new CarBrandException("Brand not found: " + id));

        brand.setBrandName(updatedCarBrandDto.getBrandName());
        brand.setEmployeeNum(updatedCarBrandDto.getEmployeeNum());
        carBrandRepository.save(brand);

        return CarBrandMapper.mapToCarBrandResponse(brand);
    }
}
