package org.example;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<CarBrand> brandsList = new ArrayList<>();
        ArrayList<CarModel> toyotaModels = new ArrayList<>();
        ArrayList<CarModel> hondaModels = new ArrayList<>();

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

        toyota.setModels(toyotaModels);
        honda.setModels(hondaModels);

        toyota.getModels().forEach(model -> model.setCarBrand(toyota));
        honda.getModels().forEach(model -> model.setCarBrand(honda));

        System.out.println("---- Task2 ----");
        brandsList.forEach(brand -> {
            System.out.println(brand);
            brand.getModels().forEach(model -> {
                System.out.println("   " + model);
            });
        });

        System.out.println("---- Task3 ----");
        Set<CarModel> setOfModels = brandsList.stream()
                .flatMap(brand -> brand.getModels().stream())
                .collect(Collectors.toSet());
        System.out.println("All car models using stream API");
        setOfModels.stream().forEach(model -> System.out.println(model));

        System.out.println("---- Task4 ----");
        List<CarModel> filteredAndSorted = brandsList.stream()
                .flatMap(brand -> brand.getModels().stream())
                .filter(model -> model.getVMax() > 190)
                .sorted(Comparator.comparing(CarModel::getModelName))
                .collect(Collectors.toList());
        System.out.println("Filtered and sorted car models: ");
        filteredAndSorted.forEach(System.out::println);

        System.out.println("---- Task5 ----");
        List<CarModelDTO> modelsDTO = filteredAndSorted.stream()
                .map(model -> CarModelDTO.builder()
                        .modelName(model.getModelName())
                        .doors(model.getDoors())
                        .vMax(model.getVMax())
                        .carBrand(model.getCarBrand().getBrandName())
                        .build())
                .sorted(Comparator.comparing(CarModelDTO::getVMax))
                .collect(Collectors.toList());
        System.out.println("List of car models DTO sorted by V max: ");
        modelsDTO.forEach(modelDTO -> System.out.println(modelDTO));

        System.out.println("---- Task6 ----");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("listOfBrands.bin"))) {
            objectOutputStream.writeObject(brandsList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("listOfBrands.bin"))) {
            List<CarBrand> decodedList = (List<CarBrand>) objectInputStream.readObject();
            decodedList.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("---- Task7 ----");
        int poolSize = 2;
        ForkJoinPool customThreadPool = new ForkJoinPool(poolSize);
        brandsList.parallelStream().forEach(brand -> {
            System.out.println(brand);
            brand.getModels().forEach(model ->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(" " + model);
            });
    });
    }
}