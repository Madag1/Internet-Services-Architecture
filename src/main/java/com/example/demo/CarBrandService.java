package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
public interface CarBrandService {
    void addCarBrand(CarBrand city);
    List<CarBrandDTO> getAllCarBrands();

    CarBrand getCarBrandByBrandName(String brandName);
}
