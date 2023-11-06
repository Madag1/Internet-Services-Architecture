package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
public interface CarModelService {
    void addCarModel(CarModelDTO carModelDTO);
    void addCarModel(CarModel model);
    List<CarModelDTO> getAllCarModels();
    void deleteCarModel(CarModel selectedModel);
    CarModel getCarModelByModelName(String modelName);
}
