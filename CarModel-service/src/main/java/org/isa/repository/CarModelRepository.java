package org.isa.repository;


import org.isa.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarModelRepository extends JpaRepository<CarModel, UUID> {
    @Override
    List<CarModel> findAll();

    @Query("select c from CarModel c where c.name = :name and c.doors = :doors")
    Optional<CarModel> findCarModelByNameAndDoors(
            @Param("name") String name,
            @Param("doors") int doors);

    Optional<CarModel> findByName(String name);
}
