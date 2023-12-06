package org.isa.repository;

import org.isa.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarBrandRepository extends JpaRepository<CarBrand, UUID> {
    Optional<Object> findByName(String name);
}
