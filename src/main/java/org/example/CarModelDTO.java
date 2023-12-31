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
public class CarModelDTO implements Comparable<CarModelDTO>, Serializable {
    private String modelName;
    private int doors;
    private int vMax;
    private String carBrand;

    @Override  //comparison (natural ordering)
    public int compareTo(CarModelDTO other){
        return this.modelName.compareTo(other.modelName);
    }
}
