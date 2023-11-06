package com.example.demo;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
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
    public void addCarModel(CarModelDTO carModelDTO) {
        CarBrand brand = null;
        try {
            brand = carBrandRepository.findByBrandName(carModelDTO.getModelName()).orElseThrow(SQLException::new);
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
    public List<CarModelDTO> getAllCarModels() {
        List<CarModel> citizens = carModelRepository.findAll();

        List<CarModelDTO> collect = citizens.stream()
                .map(CarModelMapper::mapToCarModelDto)
                .collect(Collectors.toList());
        return collect;
    }
    @Override
    public CarModel getCarModelByModelName(String modelName) {
        return carModelRepository.getCarModelByModelName(modelName);
    }
    @Override
    public void deleteCarModel(CarModel selectedModel) {
        carModelRepository.delete(selectedModel);
    }
}
