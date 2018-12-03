package com.app.controller;

import com.app.Service.AppService;
import com.app.exception.AppException;
import com.app.model.Result;
import com.app.model.Triangle;
import com.app.model.TriangleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class RestCtrl {

    @Autowired
    private AppService appService;

    /**
     * This method retrieve triangle type from service layer
     * @param triangle
     * @return ResponseEntity
     */
    @PostMapping("/triangleType")
    public ResponseEntity retrieveTriangleType(@Valid @RequestBody Triangle triangle) {
        TriangleType triangleType = appService.findTriangleType(triangle);

        Result result = new Result();
        result.setData(triangleType.getDisplayName());

        return ResponseEntity.ok(result);
    }


}
