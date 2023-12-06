package org.isa.controller;

import lombok.RequiredArgsConstructor;
import org.isa.dto.carBrand.GetCarModelCarModelNameResponse;
import org.isa.dto.carBrand.GetCarModelResponse;
import org.isa.dto.carBrand.GetCarModelsResponse;
import org.isa.dto.carBrand.PutCarModelRequest;
import org.isa.exception.CarModelNotFoundException;
import org.isa.exception.CarBrandNotFoundException;
import org.isa.service.CarModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carModels")
@RequiredArgsConstructor
public class CarModelController {
    private final CarModelService carModelService;

    @PostMapping
    public ResponseEntity<Void> addNewCarModel(@RequestBody GetCarModelResponse getCarModelResponse) {
        getCarModelResponse.setId(UUID.randomUUID());
        try {
            carModelService.addCarModel(getCarModelResponse);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CarBrandNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCarModelCarModelNameResponse> getCarModelById(@PathVariable UUID id) {
        try {
            GetCarModelCarModelNameResponse carModel = carModelService.getCarModelById(id);
            return new ResponseEntity<>(carModel, HttpStatus.OK);
        } catch (CarModelNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCarModelResponse> updateCarModel(
            @PathVariable UUID id,
            @RequestBody PutCarModelRequest putCarModelRequest) {
        try {
            GetCarModelResponse updatedCarModel = carModelService.updateCarModel(id, putCarModelRequest);
            return new ResponseEntity<>(updatedCarModel, HttpStatus.OK);
        } catch (CarModelNotFoundException | CarBrandNotFoundException e
        ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable UUID id) {
        boolean deleted = carModelService.deleteCarModel(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<GetCarModelsResponse> getAllCarModels() {
        GetCarModelsResponse carModels = carModelService.getAllCarModels();
        return new ResponseEntity<>(carModels, HttpStatus.OK);
    }
}


