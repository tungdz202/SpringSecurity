/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.DTO.UserDTO;
import com.example.SpringSecurity.DTO.request.UserRequestDTO;
import com.example.SpringSecurity.Entity.UserEntity;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public interface UserService {
    public List<UserDTO> getListUser();

    public UserDTO getUserbyId(int id);

    public UserDTO searchUserbyName(String keyword);
    
    public List<UserDTO> searchUserbyNameandEmail(String username, String email);

    public UserDTO CreateUser(UserRequestDTO user);
    
    public String updateUser(int id,UserRequestDTO userRequestDTO);

    public String DeleteUser(int id);
}
