package org.example.service;

import org.example.dto.GetCarBrandsResponse;
import org.example.dto.GetCarBrandResponse;
import org.example.dto.PutCarBrandRequest;

import java.util.UUID;

public interface CarBrandService {
    void addCarBrand(GetCarBrandResponse getCarBrandResponse);

    GetCarBrandsResponse getAllCarBrands();

    GetCarBrandResponse getCarBrandById(UUID id);

    boolean deleteCarBrand(UUID id);

    GetCarBrandResponse updateCarBrand(UUID id, PutCarBrandRequest putCarBrandRequest);
}
