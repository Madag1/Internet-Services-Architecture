package org.example;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletionException;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data //getter, setter, text representation
@ToString(exclude = "models")
@EqualsAndHashCode(exclude = "models") //hash comparison
@Builder //builder
public class CarBrand implements Comparable<CarBrand>, Serializable {
    private String brandName;
    private int employeeNum;
    private List<CarModel> models;

    @Override  //comparison (natural ordering)
    public int compareTo(CarBrand other){
        return this.brandName.compareTo(other.brandName);
    }
}