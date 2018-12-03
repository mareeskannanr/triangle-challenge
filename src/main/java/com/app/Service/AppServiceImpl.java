package com.app.Service;

import com.app.exception.AppException;
import com.app.model.Triangle;
import com.app.model.TriangleType;
import com.app.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppServiceImpl implements AppService {

    /**
     * {@inheritDoc}
     */
    @Override
    public TriangleType findTriangleType(Triangle triangle) {

        Integer a = triangle.getSideA();
        Integer b = triangle.getSideB();
        Integer c = triangle.getSideC();

        if (a == b && b == c) {
            return TriangleType.EQUILATERAL;
        }

        if (a >= b+c || c >= b+a || b >= a+c) {
            throw new AppException(HttpStatus.BAD_REQUEST, Arrays.asList(new String[] {
                    AppConstants.INVALID_DETAILS
            }));
        }

        if (b==c || a==b || c==a) {
            return TriangleType.ISOSCELES;
        }

        return TriangleType.SCALENE;
    }

}
