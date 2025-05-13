package com.quodex.monteluxe.mapper;

import com.quodex.monteluxe.dto.AddressDTO;
import com.quodex.monteluxe.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "user.id", target = "userId")
    AddressDTO toDTO(Address address);

    @Mapping(source = "userId", target = "user.id")
    Address toEntity(AddressDTO addressDTO);

}
