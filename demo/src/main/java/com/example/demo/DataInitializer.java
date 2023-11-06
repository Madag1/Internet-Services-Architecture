package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class DataInitializer {
    private final CarBrandService carBrandService;
    private final CarModelService carModelService;

    @Autowired
    public DataInitializer(CarBrandService carBrandService, CarModelService carModelService) {
        this.carBrandService = carBrandService;
        this.carModelService = carModelService;
    }
    @PostConstruct
    public void loadData() {
        ArrayList<CarBrand> brandsList = new ArrayList<>();
        ArrayList<CarModel> toyotaModels = new ArrayList<>();
        ArrayList<CarModel> hondaModels = new ArrayList<>();
        ArrayList<CarModel> allModels = new ArrayList<>();

        CarBrand toyota = CarBrand.builder()
                .brandName("Toyota")
                .employeeNum(100000)
                .models(toyotaModels)
                .models(toyotaModels)
                .build();

        CarBrand honda = CarBrand.builder()
                .brandName("Honda")
                .employeeNum(80000)
                .models(hondaModels)
                .build();
        brandsList.add(toyota);
        brandsList.add(honda);

        CarModel corolla = CarModel.builder()
                .modelName("Corolla")
                .doors(4)
                .vMax(180)
                .build();

        CarModel camry = CarModel.builder()
                .modelName("Camry")
                .doors(4)
                .vMax(200)
                .build();
        toyotaModels.add(corolla);
        toyotaModels.add(camry);
        allModels.add(corolla);
        allModels.add(camry);

        CarModel accord = CarModel.builder()
                .modelName("Accord")
                .doors(4)
                .vMax(210)
                .build();

        CarModel civic = CarModel.builder()
                .modelName("Civic")
                .doors(4)
                .vMax(190)
                .build();

        CarModel crv = CarModel.builder()
                .modelName("Cr-V")
                .doors(4)
                .vMax(195)
                .build();

        hondaModels.add(accord);
        hondaModels.add(civic);
        hondaModels.add(crv);
        allModels.add(accord);
        allModels.add(civic);
        allModels.add(crv);

        toyota.setModels(toyotaModels);
        honda.setModels(hondaModels);

        toyota.getModels().forEach(model -> model.setCarBrand(toyota));
        honda.getModels().forEach(model -> model.setCarBrand(honda));

        brandsList.forEach(brand -> carBrandService.addCarBrand(brand));
        allModels.forEach(model -> carModelService.addCarModel(model));
    }
}
