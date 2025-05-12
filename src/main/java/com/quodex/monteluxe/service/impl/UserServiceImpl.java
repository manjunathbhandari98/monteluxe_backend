package com.quodex.monteluxe.service.impl;

import com.quodex.monteluxe.dto.RegisterRequest;
import com.quodex.monteluxe.dto.UserDTO;
import com.quodex.monteluxe.jwt.JwtService;
import com.quodex.monteluxe.mapper.UserMapper;
import com.quodex.monteluxe.model.User;
import com.quodex.monteluxe.repository.UserRepository;
import com.quodex.monteluxe.service.AuthorizationService;
import com.quodex.monteluxe.service.UserService;
import com.quodex.monteluxe.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthorizationService authorizationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           PasswordEncoder passwordEncoder, JwtService jwtService,
                           AuthorizationService authorizationService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authorizationService = authorizationService;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email));
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserDTO updateUser(String id, UserDTO userDTO, String authHeader) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        String requesterEmail = jwtService.extractEmailFromHeader(authHeader);
        if (authorizationService.canAccessUser(user.getEmail(), requesterEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized to update this user");
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String targetEmail, String authHeader) {
        String requesterEmail = jwtService.extractEmailFromHeader(authHeader);
        if (authorizationService.canAccessUser(targetEmail, requesterEmail)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized to delete this user");
        }
        userRepository.deleteByEmail(targetEmail);
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        // Set role: if provided in request, use it; otherwise default to CUSTOMER
        Role role = request.getRole() != null ? request.getRole() : Role.CUSTOMER;
        user.setRole(role);
        return userRepository.save(user);
    }

}
