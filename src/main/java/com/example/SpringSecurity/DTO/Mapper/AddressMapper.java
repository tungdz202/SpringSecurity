/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.DTO.Mapper;

import com.example.SpringSecurity.DTO.AddressDTO;
import com.example.SpringSecurity.Entity.Address;

/**
 *
 * @author ADMIN
 */
public class AddressMapper {

    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO tmp = new AddressDTO();
        tmp.setId(address.getId());
        tmp.setName(address.getName());
        tmp.setCreated_at(address.getCreated_at());
        tmp.setUpdated_at(address.getUpdated_at());
        return tmp;
    }
}
