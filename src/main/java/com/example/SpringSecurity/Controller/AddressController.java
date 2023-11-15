/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.DTO.AddressDTO;
import com.example.SpringSecurity.DTO.GeneralResponse;
import com.example.SpringSecurity.Entity.Address;
import com.example.SpringSecurity.Service.AddressService;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public GeneralResponse<?> getListAddress() {
        List<AddressDTO> address = addressService.getListAddress();
        return GeneralResponse.ok(address);
    }

    @GetMapping("/{id}")
    public GeneralResponse<?> getAddressById(@PathVariable int id) {
        AddressDTO address = addressService.getAddressbyId(id);
        return GeneralResponse.ok(address);
    }

    @GetMapping("/search")
    public GeneralResponse<?> searchAddress(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        String decodedKeyword = URLDecoder.decode(name, StandardCharsets.UTF_8);
        AddressDTO address = addressService.getAddressbyName(decodedKeyword);
        return GeneralResponse.ok(address);
    }

    @PostMapping("/create")
    public GeneralResponse<?> createAddress(@RequestParam String name) {
        AddressDTO newAddress = addressService.CreateAddress(name);
        return GeneralResponse.ok(newAddress);
      
    }

    @PutMapping("/update/{id}")
    public GeneralResponse<?> updateAdress(@PathVariable int id, @RequestParam String name) {
        Address addressInfor = new Address();
        addressInfor.setId(id);
        addressInfor.setName(name);
        String message = addressService.updateAddress(addressInfor);
        return GeneralResponse.ok(message);
    }

    @DeleteMapping({"/detele/{id}"})
    public ResponseEntity<?> deleteAdress(@PathVariable int id) {
        String message = addressService.DeleteAddress(id);
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
