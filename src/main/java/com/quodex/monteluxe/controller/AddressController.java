package com.quodex.monteluxe.controller;

import com.quodex.monteluxe.dto.AddressDTO;
import com.quodex.monteluxe.model.Address;
import com.quodex.monteluxe.repository.AddressRepository;
import com.quodex.monteluxe.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<AddressDTO> addAddress(@PathVariable String userId, @RequestBody AddressDTO addressDTO ){
        AddressDTO addressdto = addressService.addAddress(userId, addressDTO);
        return ResponseEntity.ok(addressdto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AddressDTO>> getAddressByUser(@PathVariable String userId){
        List<AddressDTO> addressDTO = addressService.getAddressByUser(userId);
        return ResponseEntity.ok(addressDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable String id, @RequestBody AddressDTO addressDTO){
        AddressDTO addressDto = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable String id){
        return addressService.deleteAddress(id);
    }
}
