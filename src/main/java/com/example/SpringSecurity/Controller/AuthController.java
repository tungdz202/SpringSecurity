/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.DTO.GeneralResponse;
import com.example.SpringSecurity.DTO.UserDTO;
import com.example.SpringSecurity.DTO.request.LoginRequestDTO;
import com.example.SpringSecurity.DTO.request.UserRequestDTO;
import com.example.SpringSecurity.Service.AuthService;
import com.example.SpringSecurity.Service.Impl.AuthServiceImpl;
import com.example.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public GeneralResponse<?> LoginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = authService.Login(loginRequestDTO);
        return GeneralResponse.ok(token);

    }

    @PostMapping("/register")
    public GeneralResponse<?> register(@RequestBody UserRequestDTO userRequestDTO) {
        UserDTO newUser = userService.CreateUser(userRequestDTO);
        return GeneralResponse.ok(newUser);
    }

}
