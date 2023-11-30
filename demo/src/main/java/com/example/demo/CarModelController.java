package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carModel")
public class CarModelController {
    private final CarModelService carModelService;

    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @PostMapping
    public ResponseEntity<Void> addNewCarModel(@RequestBody CarModelDTOResponse car) {
        car.setId(UUID.randomUUID());
        carModelService.addCarModel(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{modelName}")
    public ResponseEntity<CarModelDTOResponse> getCarModelByModelName(@PathVariable String modelName) {
        try {
            CarModelDTOResponse car = carModelService.getCarModelByModelNames(modelName);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (CarModelException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModelDTOResponse> updateCarModel(@PathVariable UUID id, @RequestBody CarModelDTOResponse updatedCarDto) {
        try {
            CarModelDTOResponse updatedCar = carModelService.updateCarModel(id, updatedCarDto);
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } catch (CarModelException | CarBrandException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{modelName}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable String modelName) {
        CarModel model = carModelService.getCarModelByModelName(modelName);
        if (model != null) {
            carModelService.deleteCarModel(model);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CarModelDTOCollection>> getAllCarModels() {
        List<CarModelDTOCollection> cars = carModelService.getAllCarModels();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

}