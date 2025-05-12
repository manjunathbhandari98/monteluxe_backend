package com.quodex.monteluxe.service;

import com.quodex.monteluxe.repository.UserRepository;
import com.quodex.monteluxe.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    public boolean isAdmin(String email) {
        return userRepository.findByEmail(email)
                .map(user -> user.getRole() == Role.ADMIN)
                .orElse(false);
    }

    public boolean canAccessUser(String targetEmail, String loggedInEmail) {
        return !targetEmail.equals(loggedInEmail) && !isAdmin(loggedInEmail);
    }
}

