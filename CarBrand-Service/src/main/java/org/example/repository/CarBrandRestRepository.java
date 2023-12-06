package org.example.repository;

import org.example.dto.GetCarBrandResponse;
import org.example.dto.PutCarBrandRequest;

import java.util.UUID;

public interface CarBrandRestRepository {
    void delete(UUID id);

    void updateName(UUID id, PutCarBrandRequest putCarBrandRequest);

    void addCarBrand(GetCarBrandResponse getCarBrandResponse);
}
