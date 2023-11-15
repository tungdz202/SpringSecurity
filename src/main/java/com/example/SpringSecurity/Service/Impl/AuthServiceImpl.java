/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Service.Impl;

import com.example.SpringSecurity.DTO.request.LoginRequestDTO;
import com.example.SpringSecurity.Entity.UserEntity;
import com.example.SpringSecurity.Exception.BusinessException;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.AUTHENTICATION_FAIL_CODE;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.AUTHENTICATION_FAIL_MESSAGE;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Security.Jwt.JwtProvider;
import com.example.SpringSecurity.Service.AuthService;
import com.example.SpringSecurity.common.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validate Validator;

    @Autowired
    private JwtProvider jwtConfig;

    @Override
    public boolean checkUser(String username, String password) {
        try {
            UserEntity user = userRepository.findByName(username);
            if (password.equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("user không tồn tại");
        }
        return false;
    }

    @Override
    public String Login(LoginRequestDTO loginRequestDTO) {
        Validator.validate(loginRequestDTO);
        UserEntity user = userRepository.findByName(loginRequestDTO.getName());
        if (user != null) {
            if (loginRequestDTO.getPassword().equals(user.getPassword())) {
                String token = jwtConfig.generateToken(user.getName());
                return token;
            } else {
                throw new BusinessException(AUTHENTICATION_FAIL_CODE, AUTHENTICATION_FAIL_MESSAGE);
            }
        } else {
            throw new BusinessException(AUTHENTICATION_FAIL_CODE, AUTHENTICATION_FAIL_MESSAGE);
        }

    }
}
