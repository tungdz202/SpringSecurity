/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.DTO.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "Tên đăng nhập không được để trống!")
    private String name;

    // Minimum 8 characters, at least one letter and one number
    @NotBlank(message = "Mật khẩu không được để trống!")
    private String password;
}
