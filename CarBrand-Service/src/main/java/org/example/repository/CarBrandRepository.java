package org.example.repository;

import org.example.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarBrandRepository extends JpaRepository<CarBrand, UUID> {
    Optional<CarBrand> findByName(String name);

    @Override
    List<CarBrand> findAll();

    CarBrand getCarBrandByName(String name);
}
