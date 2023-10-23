package org.example;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString//(exclude = "carBrand")
@EqualsAndHashCode
@Builder
public class CarModel implements Comparable<CarModel>, Serializable {
    private String modelName;
    private int doors;
    private int vMax;
    private CarBrand carBrand;

    @Override
    public int compareTo(CarModel other) {
        return this.modelName.compareTo(other.modelName);
    }
}
