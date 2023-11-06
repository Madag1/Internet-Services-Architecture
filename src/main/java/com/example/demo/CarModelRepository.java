package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CarModelRepository extends JpaRepository<CarModel, UUID> {
    @Override
    List<CarModel> findAll();
    CarModel getCarModelByModelName(String modelName);
    @Query("SELECT c FROM CarModel c WHERE c.modelName = :modelName and c.vMax = :vMax")
    Optional<CarModel> findCarModelByModelNameAndVMax(
            @Param("modelName") String modelName,
            @Param("vMax") int vMax
    );
}
