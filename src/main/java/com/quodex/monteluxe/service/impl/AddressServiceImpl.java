package com.quodex.monteluxe.service.impl;

import com.quodex.monteluxe.dto.AddressDTO;
import com.quodex.monteluxe.mapper.AddressMapper;
import com.quodex.monteluxe.model.Address;
import com.quodex.monteluxe.model.User;
import com.quodex.monteluxe.repository.AddressRepository;
import com.quodex.monteluxe.repository.UserRepository;
import com.quodex.monteluxe.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository,
                              AddressMapper addressMapper
                              ){
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.addressMapper = addressMapper;
    }
    @Override
    public AddressDTO addAddress(String userId, AddressDTO addressDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if(user != null){
            addressDTO.setUserId(user.getId());
            Address address = addressMapper.toEntity(addressDTO);
            Address savedAddress = addressRepository.save(address);
            return addressMapper.toDTO(savedAddress);
        }
        return null;
    }

    @Override
    public List<AddressDTO> getAddressByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user != null){
            List<Address> addresses = addressRepository.findByUserId(userId);
            return addresses.stream().map(addressMapper::toDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public AddressDTO updateAddress(String id, AddressDTO dto) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address Not found"));

        address.setFullName(dto.getFullName());
        address.setCity(dto.getCity());
        address.setEmail(dto.getEmail());
        address.setCountry(dto.getCountry());
        address.setDefault(dto.isDefault());
        address.setPhone(dto.getPhone());
        address.setPostalCode(dto.getPostalCode());
        address.setState(dto.getState());
        address.setStreet(dto.getStreet());

        Address newAddress = addressRepository.save(address);
        return addressMapper.toDTO(newAddress);
    }

    @Override
    public ResponseEntity<String> deleteAddress(String id) {
        addressRepository.deleteById(id);
        return ResponseEntity.ok("Address Deleted Successfully");
    }
}
