/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.common;

import com.example.SpringSecurity.Exception.BusinessException;
import com.example.SpringSecurity.Exception.HttpStatusConstant;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class Validate {
     @Autowired
    private Validator validator;

    public void validate(Object dto) {
        List<String> errors = new ArrayList<>();
        validator.validate(dto)
                .forEach(e -> errors.add(e.getMessage()));
        if (!errors.isEmpty()) {
            throw new BusinessException(HttpStatusConstant.INVALID_DATA_CODE, errors.get(0));
        }
    }
    
}
