package org.isa.service;


import org.isa.dto.carModel.GetCarBrandResponse;
import org.isa.dto.carModel.PutCarBrandRequest;

import java.util.List;
import java.util.UUID;

public interface CarBrandService {
    void addCarBrand(GetCarBrandResponse getCarBrandResponse);

    boolean deleteCarBrand(UUID id);

    GetCarBrandResponse updateCarBrand(UUID id, PutCarBrandRequest putCarBrandRequest);

    List<GetCarBrandResponse> getAllCarBrands();

    GetCarBrandResponse getCarBrandById(UUID id);
}
