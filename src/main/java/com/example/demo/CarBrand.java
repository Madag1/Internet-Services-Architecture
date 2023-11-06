package com.example.demo;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionException;

import jakarta.persistence.*;
import lombok.*;

@Data //getter, setter, text representation
@ToString(exclude = "models")
@EqualsAndHashCode(exclude = "models") //hash comparison
@Builder //builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="car_brands")
public class CarBrand implements Comparable<CarBrand>, Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private String brandName;
    private int employeeNum;
    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarModel> models;

    @Override  //comparison (natural ordering)
    public int compareTo(CarBrand other){
        return this.brandName.compareTo(other.brandName);
    }
}