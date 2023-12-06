package org.isa.utils;

import jakarta.annotation.PostConstruct;
import org.isa.model.CarModel;
import org.isa.model.CarBrand;
import org.isa.repository.CarModelRepository;
import org.isa.repository.CarBrandRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader {


    private final CarModelRepository carModelRepository;
    private final CarBrandRepository carBrandRepository;

    public DataLoader(CarModelRepository carModelRepository, CarBrandRepository carBrandRepository) {
        this.carModelRepository = carModelRepository;
        this.carBrandRepository = carBrandRepository;
    }

    @PostConstruct
    public void persistData() {
        List<CarBrand> carBrands = loadCarBrands();
        carBrandRepository.saveAll(carBrands);
        List<CarModel> listOfCarModels = loadCarModels(carBrands);
        loadCarModelsToCarBrand(carBrands, listOfCarModels);
        carModelRepository.saveAll(listOfCarModels);
        carBrandRepository.saveAll(carBrands);

    }

    public void loadCarModelsToCarBrand(List<CarBrand> carBrands, List<CarModel> listOfCarModels) {
        carBrands.get(0).setCarModels(Arrays.asList(listOfCarModels.get(0), listOfCarModels.get(1)));
        carBrands.get(0).setCarModels(Arrays.asList(listOfCarModels.get(2), listOfCarModels.get(3)));
        carBrands.get(0).setCarModels(Arrays.asList(listOfCarModels.get(4), listOfCarModels.get(5)));
        carBrands.get(0).setCarModels(Collections.singletonList(listOfCarModels.get(6)));

    }


    public List<CarModel> loadCarModels(List<CarBrand> carBrands) {
        List<CarModel> listOfCarModels = new ArrayList<>();
        List<UUID> uuids = Arrays.asList(
                UUID.fromString("aa2b6af2-3c14-4784-b524-110798769d72"),
                UUID.fromString("1c2b8bdb-f83f-4594-a18b-4073ec5b5afb"),
                UUID.fromString("055555d6-1cd0-4268-a519-176381ed2799"),
                UUID.fromString("8b0454ff-e4f7-4d4d-ae16-4c84162a1c5a"),
                UUID.fromString("445cfb56-ca51-4da3-b244-dfc108460981"),
                UUID.fromString("04dbe36d-e6ca-4030-a3e9-b202967de4cd"),
                UUID.fromString("9d1d3e0a-bb79-4de4-a836-87ca02216846")

        );
        listOfCarModels.add(CarModel.builder().id(uuids.get(0))
                .name("Corolla").doors(4)
                .carBrand(carBrands.get(0)).build());
        listOfCarModels.add(CarModel.builder().id(uuids.get(1))
                .name("Supra").doors(2)
                .carBrand(carBrands.get(0)).build());
        listOfCarModels.add(CarModel.builder().id(uuids.get(2))
                .name("Civic").doors(4)
                .carBrand(carBrands.get(1)).build());
        listOfCarModels.add(CarModel.builder().id(uuids.get(3))
                .name("Accord").doors(4)
                .carBrand(carBrands.get(1)).build());
        listOfCarModels.add(CarModel.builder().id(uuids.get(4))
                .name("M3").doors(4)
                .carBrand(carBrands.get(2)).build());
        listOfCarModels.add(CarModel.builder().id(uuids.get(5))
                .name("X8").doors(4)
                .carBrand(carBrands.get(2)).build());
        listOfCarModels.add(CarModel.builder().id(uuids.get(6))
                .name("Mondeo").doors(4)
                .carBrand(carBrands.get(3)).build());
        return listOfCarModels;
    }

    public List<CarBrand> loadCarBrands() {
        List<CarBrand> listOfCarBrands = new ArrayList<>();
        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("e340ba5d-de57-47db-a56f-5bd788f4d183"))
                .name("BMW").build());

        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("3851dc62-52b7-481a-adcd-0287c16298b5"))
                .name("Honda").build());

        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("fd08d341-a0b4-4a32-8251-903178a6daa4"))
                .name("Toyota").build());

        listOfCarBrands.add(CarBrand.builder().id(UUID.fromString("df6dfefa-107d-4791-84be-f548b1a9a902"))
                .name("Ford").build());

        return listOfCarBrands;
    }

}
