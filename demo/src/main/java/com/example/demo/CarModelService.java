package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

public interface CarModelService {
    void addCarModel(CarModelDTOResponse carModelDTO);
    void addCarModel(CarModel model);
    List<CarModelDTOCollection> getAllCarModels();
    void deleteCarModel(CarModel selectedModel);
    CarModel getCarModelByModelName(String name);
    CarModelDTOResponse getCarModelByModelNames(String name);

    CarModelDTOResponse updateCarModel(UUID id, CarModelDTOResponse updatedCarModelDto);
}
