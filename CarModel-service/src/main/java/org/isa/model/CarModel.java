package org.isa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "carModels")
@AllArgsConstructor
@NoArgsConstructor
public class CarModel implements Serializable {
    @Id
    @Column(unique = true)
    private UUID id;
    private String name;
    private int doors;
    @ManyToOne
    @JoinColumn(name = "carBrand_id")
    private CarBrand carBrand;

}
