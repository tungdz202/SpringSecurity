/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Service.Impl;

import com.example.SpringSecurity.DTO.AddressDTO;
import com.example.SpringSecurity.DTO.Mapper.AddressMapper;
import com.example.SpringSecurity.Entity.Address;
import com.example.SpringSecurity.Exception.BusinessException;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.ADDRESS_EXIST_MESSAGE;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.ADDRESS_NOT_FOUND_MESSAGE;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.EXIST_CODE;
import static com.example.SpringSecurity.Exception.HttpStatusConstant.NOT_FOUND_CODE;
import com.example.SpringSecurity.Repository.AddressRepository;
import com.example.SpringSecurity.Service.AddressService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDTO> getListAddress() {
        List<AddressDTO> results = new ArrayList<AddressDTO>();
        List<Address> listaddress = addressRepository.findAll();
        for (Address address : listaddress) {
            results.add(AddressMapper.toAddressDTO(address));
        }
        return results;
    }

    @Override
    public AddressDTO getAddressbyId(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new BusinessException(NOT_FOUND_CODE, ADDRESS_NOT_FOUND_MESSAGE));
        return AddressMapper.toAddressDTO(address);
    }

    @Override
    public AddressDTO getAddressbyName(String keyword) {
        Address address = addressRepository.findByName(keyword);
        if (address == null) {
            log.debug("address: \"" + keyword + "\" doesn't exist");
            throw new BusinessException(NOT_FOUND_CODE, ADDRESS_NOT_FOUND_MESSAGE);
        }
        return AddressMapper.toAddressDTO(address);
    }

    @Override
    public AddressDTO CreateAddress(String address) {
        if (checkExist(address)) {
            throw new BusinessException(EXIST_CODE, ADDRESS_EXIST_MESSAGE);
        } else {
            Address addressDetail = new Address();
            addressDetail.setName(address);
            addressRepository.save(addressDetail);
            return AddressMapper.toAddressDTO(addressDetail);
        }
    }

    @Override
    public String updateAddress(Address address) {
        if (addressRepository.existsById(address.getId())) {
            addressRepository.save(address);
            return "Cập nhật thành công";
        } else {
            throw new BusinessException(NOT_FOUND_CODE, ADDRESS_NOT_FOUND_MESSAGE);
        }

    }

    @Override
    public String DeleteAddress(int id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return "Xoá thành công";// Xóa thực thể nếu tồn tại
        } else {
            throw new BusinessException(NOT_FOUND_CODE, ADDRESS_NOT_FOUND_MESSAGE);
        }

    }

    public boolean checkExist(String name) {
        if (addressRepository.findByName(name) != null) {
            return true;
        } else {
            return false;
        }
    }
}
