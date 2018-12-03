package com.app.model;

import com.app.util.AppConstants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Triangle {

    @NotNull(message = AppConstants.SIDE_A_REQUIRED)
    @Min(value = 1, message = AppConstants.SIDE_A_INVALID)
    private Integer sideA;

    @NotNull(message = AppConstants.SIDE_B_REQUIRED)
    @Min(value = 1, message = AppConstants.SIDE_B_INVALID)
    private Integer sideB;

    @NotNull(message = AppConstants.SIDE_C_REQUIRED)
    @Min(value = 1, message = AppConstants.SIDE_C_INVALID)
    private Integer sideC;

    public Triangle() {}

    public Integer getSideA() {
        return sideA;
    }

    public void setSideA(Integer sideA) {
        this.sideA = sideA;
    }

    public Integer getSideB() {
        return sideB;
    }

    public void setSideB(Integer sideB) {
        this.sideB = sideB;
    }

    public Integer getSideC() {
        return sideC;
    }

    public void setSideC(Integer sideC) {
        this.sideC = sideC;
    }

    @Override
    public String toString() {
        return "Triangle [sideA=" + sideA + ", sideB=" + sideB + ", sideC=" + sideC + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return hashCode() == triangle.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSideA(), getSideB(), getSideC());
    }
}