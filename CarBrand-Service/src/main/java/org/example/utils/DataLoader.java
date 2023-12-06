package org.example.utils;

import jakarta.annotation.PostConstruct;
import org.example.model.CarBrand;
import org.example.repository.CarBrandRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataLoader {


    private final CarBrandRepository carBrandRepository;

    public DataLoader(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    private static List<CarBrand> loadCarBrands() {
        List<CarBrand> listOfCarBrands = new ArrayList<>();
        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("e340ba5d-de57-47db-a56f-5bd788f4d183"))
                .name("Toyota").employees(200000).build());

        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("3851dc62-52b7-481a-adcd-0287c16298b5"))
                .name("Honda").employees(150000).build());

        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("fd08d341-a0b4-4a32-8251-903178a6daa4"))
                .name("BMW").employees(100000).build());

        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("df6dfefa-107d-4791-84be-f548b1a9a902"))
                .name("Ford").employees(300000).build());

        return listOfCarBrands;
    }

    @PostConstruct
    public void persistData() {
        List<CarBrand> listOfCarBrands = DataLoader.loadCarBrands();
        carBrandRepository.saveAll(listOfCarBrands);
    }
}
