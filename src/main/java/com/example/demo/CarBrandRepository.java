package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;
@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, UUID> {
    Optional<CarBrand> findByBrandName(String name);

    @Override
    List<CarBrand> findAll();
    CarBrand getCarBrandByBrandName(String brandName);
}
