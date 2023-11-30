package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carBrand")
public class CarBrandController {
    private final CarBrandService carBrandService;

    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @PostMapping
    public ResponseEntity<Void> addNewCarBrand(@RequestBody CarBrandDTO brandDto){
        brandDto.setId(UUID.randomUUID());
        carBrandService.addCarBrand(brandDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarBrandDTOResponse> updateCarBrand(@PathVariable UUID id, @RequestBody CarBrandDTOResponse updatedCarBrandDto){
        try {
            CarBrandDTOResponse updatedBrand = carBrandService.updateCarBrand(id,updatedCarBrandDto);
            return new ResponseEntity<>(updatedBrand, HttpStatus.OK);
        }
        catch(CarBrandException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CarBrandDTOCollection>> getAllCarBrands() {
        List<CarBrandDTOCollection> brands = carBrandService.getAllCarBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/{brandName}")
    public ResponseEntity<CarBrandDTOResponse> getCarBrandByBrandName(@PathVariable String brandName) {
        try {
            CarBrandDTOResponse brand = carBrandService.getCarBrandByBrandNames(brandName);
            return new ResponseEntity<>(brand, HttpStatus.OK);
        } catch (CarBrandException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
