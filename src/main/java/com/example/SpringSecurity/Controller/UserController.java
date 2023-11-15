/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.DTO.GeneralResponse;
import com.example.SpringSecurity.DTO.UserDTO;
import com.example.SpringSecurity.DTO.request.UserRequestDTO;
import com.example.SpringSecurity.Entity.UserEntity;
import com.example.SpringSecurity.Repository.AddressRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public GeneralResponse<?> getListUser() {
        List<UserDTO> users = userService.getListUser();
        return GeneralResponse.ok(users);
    }

    @GetMapping("/{id}")
    public GeneralResponse<?> getUserById(@PathVariable int id) {
        UserDTO user = userService.getUserbyId(id);
        return GeneralResponse.ok(user);
    }

    @GetMapping("/search")
    public GeneralResponse<?> searchUser(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        UserDTO user = userService.searchUserbyName(name);
        return GeneralResponse.ok(user);

    }

    @PostMapping("/create")
    public GeneralResponse<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserDTO newUser = userService.CreateUser(userRequestDTO);
        return GeneralResponse.ok(newUser);
    }

    @PutMapping("/update/{id}")
    public GeneralResponse<?> updateUser(@PathVariable int id, @RequestBody UserRequestDTO userRequestDTO) {
        String message = userService.updateUser(id, userRequestDTO);
        return GeneralResponse.ok(message);
    }
    
   
    @DeleteMapping({"/detele/{id}"})
    public GeneralResponse<?> deleteUser(@PathVariable int id) {
        String message = userService.DeleteUser(id);
        return GeneralResponse.ok(message);
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> searchUserbyNameandEmail(@RequestParam(name = "name") String name,
//        @RequestParam(name = "email") String email) {
//        List<UserDTO> users = userService.searchUserbyNameandEmail(name, email);
//        if (users.isEmpty()) {
//            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body("Không tìm thấy user");
//        } else {
//            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(users);
//        }
//
//    }

}
