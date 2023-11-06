package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineAppRunner implements CommandLineRunner {
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private CarBrandService carBrandService;

    @Transactional
    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Available commands:");
            System.out.println("1. List Categories");
            System.out.println("2. List Elements");
            System.out.println("3. Add New Element");
            System.out.println("4. Delete Element");
            System.out.println("5. Show al commands");
            System.out.println("6. Stop Application");
            System.out.print("Enter a command (1/2/3/4/5/6): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    listBrands();
                    break;
                case "2":
                    listModels();
                    break;
                case "3":
                    addNewModel();
                    break;
                case "4":
                    deleteModel();
                    break;
                case "5":
                    showCommands();
                    break;
                case "6":
                    running = false;
                    System.out.println("Application stopped.");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void listBrands() {
        List<CarBrandDTO> carBrands = carBrandService.getAllCarBrands();
        if (carBrands.isEmpty()) {
            System.out.println("There are no bands in the database!");
        }
        else {
            System.out.println("All brands: ");
            carBrands.forEach(brand -> System.out.println(brand.getBrandName()));
        }
    }

    private void listModels() {
        Scanner scanner = new Scanner(System.in);
        List<CarModelDTO> carModels = carModelService.getAllCarModels();
        System.out.println("All models :");
        CarBrand brand = null;
        while (brand == null) {
            System.out.println("Enter the category (existing category): ");
            String carBrand = scanner.next();
            brand = carBrandService.getCarBrandByBrandName(carBrand);
            if (brand == null) {
                System.out.println("Brand not found!");
            }
        }
        for(CarModelDTO model : carModels){
            if(model.getCarBrand().equals(brand.getBrandName())){
                System.out.println(model);
            }
        }
    }

    @Transactional
    private void addNewModel() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter model name: ");
        String modelName = scanner.nextLine();

        System.out.print("Enter number of doors: ");
        int doors = scanner.nextInt();

        System.out.print("Enter V-max: ");
        int vMax = scanner.nextInt();

        CarBrand brand = null;
        while (brand == null) {
            System.out.println("Enter the category (existing category): ");
            String carBrand = scanner.next();
            brand = carBrandService.getCarBrandByBrandName(carBrand);
            if (brand == null) {
                System.out.println("Brand not found!");
            }
        }
         CarModel model = CarModel.builder()
                    .id(UUID.randomUUID())
                    .modelName(modelName)
                    .doors(doors)
                    .vMax(vMax)
                    .carBrand(brand)
                    .build();

            carModelService.addCarModel(model);
            System.out.println("New model added successfully!");
    }

    @Transactional
    private void deleteModel() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter model name that will be deleted: ");
        String modelName = scanner.nextLine();
        CarModel modelToDelete = carModelService.getCarModelByModelName(modelName);
        if (modelToDelete != null) {
            carModelService.deleteCarModel(modelToDelete);
            System.out.println("Element deleted successfully.");
        } else {
            System.out.println("Element not found. Please enter an existing element.");
        }
    }

    private void showCommands(){
        System.out.println("Available commands:");
        System.out.println("1. List Categories");
        System.out.println("2. List Elements");
        System.out.println("3. Add New Element");
        System.out.println("4. Delete Element");
        System.out.println("5. Show al commands");
        System.out.println("6. Stop Application");
        System.out.print("Enter a command (1/2/3/4/5/6): ");
    }
}
