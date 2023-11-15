/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.DTO.AddressDTO;
import com.example.SpringSecurity.Entity.Address;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public interface AddressService {
    public List<AddressDTO> getListAddress();

    public AddressDTO getAddressbyId(int id);
    
    public AddressDTO getAddressbyName(String Name);

    public AddressDTO CreateAddress(String address);
    
    public String updateAddress(Address address);

    public String DeleteAddress(int id);
}
