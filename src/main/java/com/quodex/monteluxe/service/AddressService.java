package com.quodex.monteluxe.service;

import com.quodex.monteluxe.dto.AddressDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    public AddressDTO addAddress(String userId, AddressDTO addressDTO);

    List<AddressDTO> getAddressByUser(String userId);

    AddressDTO updateAddress(String id, AddressDTO addressDTO);

    ResponseEntity<String> deleteAddress(String id);
}
