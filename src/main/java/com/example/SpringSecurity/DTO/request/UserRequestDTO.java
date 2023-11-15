/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.DTO.request;

import javax.validation.constraints.Email;
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
public class UserRequestDTO {
    @NotBlank(message = "Tên đăng nhập không được để trống!")
    private String name;

    // Minimum 8 characters, at least one letter and one number
    @NotBlank(message = "Mật khẩu không được để trống!")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Mật khẩu phải có tối thiểu 8 kí tự, ít nhất 1 kí tự số và 1 kí tự chữ!")
    private String password;

    @NotBlank(message = "Bạn chưa nhập địa chỉ email!")
    @Email(regexp = ".+@.+\\..+", message = "Email chưa đúng định dạng!")
    private String email;

    @NotBlank(message = "Bạn chưa nhập số điện thoại!")
    @Pattern(regexp = "^[0-9]*$", message = "Số điện thoại không đúng định dạng!")
    private String phone;

    @NotBlank(message = "Bạn chưa nhập địa chỉ!")
    private String address;
  
}
