/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Service.Impl;

import com.example.SpringSecurity.DTO.Mapper.UserMapper;
import com.example.SpringSecurity.DTO.UserDTO;
import com.example.SpringSecurity.DTO.request.UserRequestDTO;
import com.example.SpringSecurity.Entity.UserEntity;
import com.example.SpringSecurity.Exception.BusinessException;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.EXIST_CODE;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.NOT_FOUND_CODE;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.USER_EXIST_MESSAGE;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.USER_NOT_FOUND_MESSAGE;
import com.example.SpringSecurity.Repository.AddressRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.UserService;
import com.example.SpringSecurity.common.Validate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validate Validator;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<UserDTO> getListUser() {
        List<UserDTO> results = new ArrayList<UserDTO>();
        try {
            List<UserEntity> listusers = userRepository.findAll();
            for (UserEntity user : listusers) {
                results.add(UserMapper.toUserDTO(user));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return results;

    }

    @Override
    public UserDTO getUserbyId(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new BusinessException(NOT_FOUND_CODE, USER_NOT_FOUND_MESSAGE));
        return UserMapper.toUserDTO(user);

    }

    @Override
    public UserDTO searchUserbyName(String username) {
        UserEntity user = userRepository.findByName(username);
        if (user == null) {
            log.debug("username: \"" + username + "\" doesn't exist");
            throw new UsernameNotFoundException("username: \"" + username + "\" doesn't exist");
        }
        return UserMapper.toUserDTO(user);
    }

    @Override
    public UserDTO CreateUser(UserRequestDTO userRequestDTO) {
        Validator.validate(userRequestDTO);
        UserEntity user = new UserEntity();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());
        user.setRole(1);
        user.setAddress(addressRepository.findByName(userRequestDTO.getAddress()));
        if (checkExist(user.getName())) {
            throw new BusinessException(EXIST_CODE, USER_EXIST_MESSAGE);
        } else {
            userRepository.save(user);
            return UserMapper.toUserDTO(user);
        }
    }

    @Override
    public String DeleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Xoá thành công";// Xóa thực thể nếu tồn tại
        } else {
            throw new BusinessException(NOT_FOUND_CODE, USER_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public String updateUser(int id, UserRequestDTO userRequestDTO) {
        Validator.validate(userRequestDTO);
        if (userRepository.existsById(id)) {
            UserEntity user = new UserEntity();
            user.setId(id);
            user.setName(userRequestDTO.getName());
            user.setEmail(userRequestDTO.getEmail());
            user.setPassword(userRequestDTO.getPassword());
            user.setPhone(userRequestDTO.getPhone());
            user.setRole(1);
            user.setAddress(addressRepository.findByName(userRequestDTO.getAddress()));
            userRepository.save(user);
            return "Cập nhật thành công";
        } else {
            throw new BusinessException(NOT_FOUND_CODE, USER_NOT_FOUND_MESSAGE);
        }
    }

    public boolean checkExist(String name) {
        if (userRepository.findByName(name) != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<UserDTO> searchUserbyNameandEmail(String username, String email) {
        List<UserDTO> results = new ArrayList<UserDTO>();
        try {

            List<UserEntity> listuser = userRepository.findUsersByUsernameAndEmail(username, email);
            for (UserEntity user : listuser) {
                results.add(UserMapper.toUserDTO(user));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

}
