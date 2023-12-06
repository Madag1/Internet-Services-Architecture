package org.isa.controller;

import lombok.RequiredArgsConstructor;
import org.isa.dto.carModel.GetCarBrandResponse;
import org.isa.dto.carModel.PutCarBrandRequest;
import org.isa.exception.CarBrandNotFoundException;
import org.isa.service.CarBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carBrands")
@RequiredArgsConstructor
public class CarBrandController {
    private final CarBrandService carBrandService;

    @PostMapping
    public ResponseEntity<Void> addNewCarBrand(@RequestBody GetCarBrandResponse getCarBrandResponse) {
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
    public ResponseEntity<List<GetCarBrandResponse>> getAllCarBrands() {
        List<GetCarBrandResponse> carBrands = carBrandService.getAllCarBrands();
        return new ResponseEntity<>(carBrands, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable UUID id) {
        boolean deleted = carBrandService.deleteCarBrand(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


