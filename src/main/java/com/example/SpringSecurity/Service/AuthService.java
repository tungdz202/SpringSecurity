/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.DTO.request.LoginRequestDTO;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public interface AuthService {
     public boolean checkUser(String username, String password);
     public String Login(LoginRequestDTO loginRequestDTO);
}
