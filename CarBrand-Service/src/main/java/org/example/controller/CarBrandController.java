package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.GetCarBrandsResponse;
import org.example.dto.GetCarBrandResponse;
import org.example.dto.PutCarBrandRequest;
import org.example.exception.CarBrandNotFoundException;
import org.example.service.CarBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carBrands")
@RequiredArgsConstructor
public class CarBrandController {
    private final CarBrandService carBrandService;

    @PostMapping
    public ResponseEntity<Void> addNewCarBrand(@RequestBody GetCarBrandResponse getCarBrandResponse) {
        getCarBrandResponse.setId(UUID.randomUUID());
        carBrandService.addCarBrand(getCarBrandResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCarBrandResponse> updateCarBrand(
            @PathVariable UUID id,
            @RequestBody PutCarBrandRequest putCarBrandRequest
    ) {
        try {
            GetCarBrandResponse updatedCarBrand = carBrandService.updateCarBrand(id, putCarBrandRequest);
            return new ResponseEntity<>(updatedCarBrand, HttpStatus.OK);
        } catch (CarBrandNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<GetCarBrandsResponse> getAllCarBrands() {
        GetCarBrandsResponse carBrands = carBrandService.getAllCarBrands();
        return new ResponseEntity<>(carBrands, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCarBrandResponse> getCarBrandById(@PathVariable UUID id) {
        try {
            GetCarBrandResponse carBrand = carBrandService.getCarBrandById(id);
            return new ResponseEntity<>(carBrand, HttpStatus.OK);
        } catch (CarBrandNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable UUID id) {
        boolean deleted = carBrandService.deleteCarBrand(id);
        if (deleted) {
            String message = "Car brand with ID " + id + " was deleted successfully";
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


