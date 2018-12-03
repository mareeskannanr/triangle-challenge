package com.app.Service;

import com.app.model.Triangle;
import com.app.model.TriangleType;

public interface AppService {

    /**
     * This Method determine type of triangle based on value of each side
     * @param triangle
     * @return TriangleType
     */
    TriangleType findTriangleType(Triangle triangle);

}
