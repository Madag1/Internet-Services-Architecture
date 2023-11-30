package com.example.demo;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarModelServiceImpl implements CarModelService {
    private final CarBrandRepository carBrandRepository;
    private final CarModelRepository carModelRepository;

    public CarModelServiceImpl(CarBrandRepository carBrandRepository,
                              CarModelRepository carModelRepository) {
        this.carBrandRepository = carBrandRepository;
        this.carModelRepository = carModelRepository;
    }
    @Override
    public void addCarModel(CarModelDTOResponse carModelDTO) {
        CarBrand brand = null;
        try {
            brand = carBrandRepository.findByBrandName(carModelDTO.getCarBrand()).orElseThrow(SQLException::new);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CarModel model = CarModelMapper.mapToCarModel(carModelDTO, brand);
        carModelRepository.save(model);
    }
    @Override
    public void addCarModel(CarModel model) {
        carModelRepository.save(model);
    }

    @Override
    public List<CarModelDTOCollection> getAllCarModels() {
        List<CarModel> models = carModelRepository.findAll();

        List<CarModelDTOCollection> collect = models.stream()
                .map(CarModelMapper::mapToCarCollection)
                .collect(Collectors.toList());
        return collect;
    }
    @Override
    public void deleteCarModel(CarModel selectedModel) {
        carModelRepository.delete(selectedModel);
    }

    @Override
    public CarModel getCarModelByModelName(String modelName) {
        return carModelRepository.getCarModelByModelName(modelName);
    }
    @Override
    public CarModelDTOResponse getCarModelByModelNames(String name) {
        CarModel model = carModelRepository.findByModelName(name)
                .orElseThrow(() -> new CarModelException("Car model not found: " + name));
        return CarModelMapper.mapToCarResponse(model);
    }

    @Override
    public CarModelDTOResponse updateCarModel(UUID id, CarModelDTOResponse updatedCarModelDTO) {
        CarModel existingCarModel = carModelRepository.findById(id)
                .orElseThrow(() -> new CarModelException("Car model not found: " + id));
        existingCarModel.setModelName(updatedCarModelDTO.getModelName());
        existingCarModel.setDoors(updatedCarModelDTO.getDoors());
        existingCarModel.setVMax(updatedCarModelDTO.getVMax());
        CarBrand brand = carBrandRepository
                .findByBrandName(updatedCarModelDTO.getCarBrand())
                .orElseThrow(() -> new CarBrandException("Brand not found: " + id));
        existingCarModel.setCarBrand(brand);

        carModelRepository.save(existingCarModel);

        return CarModelMapper.mapToCarResponse(existingCarModel);
    }
}
