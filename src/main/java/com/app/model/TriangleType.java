package com.app.model;

public enum TriangleType {
    EQUILATERAL("Equilateral"), ISOSCELES("Isosceles"), SCALENE("Scalene");

    private String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    TriangleType(String displayName) {
        this.displayName = displayName;
    }

}
