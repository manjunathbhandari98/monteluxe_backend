package com.quodex.monteluxe.service;

import com.quodex.monteluxe.dto.RegisterRequest;
import com.quodex.monteluxe.dto.UserDTO;
import com.quodex.monteluxe.model.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    User register(RegisterRequest request);

    UserDTO getUserByEmail(String email);


    UserDTO updateUser(String id, UserDTO userDTO, String authHeader);

    void deleteUserByEmail(String email, String authHeader);
}
