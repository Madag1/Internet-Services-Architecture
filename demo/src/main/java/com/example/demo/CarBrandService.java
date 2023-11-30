package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

public interface CarBrandService {
    void addCarBrand(CarBrand brand);
    void addCarBrand(CarBrandDTO brand);
    List<CarBrandDTOCollection> getAllCarBrands();

    CarBrand getCarBrandByBrandName(String brandName);

    CarBrandDTOResponse getCarBrandByBrandNames(String brandName);

    boolean deleteCarBrand(UUID id);

    CarBrandDTOResponse updateCarBrand(UUID id, CarBrandDTOResponse updatedCarBrandDto);
}
