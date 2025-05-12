package com.quodex.monteluxe.mapper;

import com.quodex.monteluxe.dto.UserDTO;
import com.quodex.monteluxe.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);


}
