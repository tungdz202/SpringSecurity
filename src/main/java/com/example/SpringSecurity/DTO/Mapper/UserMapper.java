/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.DTO.Mapper;

import com.example.SpringSecurity.DTO.UserDTO;
import com.example.SpringSecurity.Entity.UserEntity;


public class UserMapper {
    public static UserDTO toUserDTO(UserEntity user){
    UserDTO tmp = new UserDTO();
    tmp.setId(user.getId());
    tmp.setName(user.getName());
    tmp.setEmail(user.getEmail());
    tmp.setPhone(user.getPhone());
    tmp.setAddress(user.getAddress().getName());
    tmp.setCreated_at(user.getCreated_at());
    tmp.setUpdated_at(user.getUpdated_at());
    return tmp;
    }
}

