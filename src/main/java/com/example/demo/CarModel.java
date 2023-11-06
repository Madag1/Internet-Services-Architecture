package com.example.demo;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@ToString//(exclude = "carBrand")
@EqualsAndHashCode
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="car_models")
public class CarModel implements Comparable<CarModel>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String modelName;
    private int doors;
    private int vMax;
    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;

    @Override
    public int compareTo(CarModel other) {
        return this.modelName.compareTo(other.modelName);
    }
}
