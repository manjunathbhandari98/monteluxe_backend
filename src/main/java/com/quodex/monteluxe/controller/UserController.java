package com.quodex.monteluxe.controller;

import com.quodex.monteluxe.dto.UserDTO;
import com.quodex.monteluxe.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        try {
            UserDTO userDTO = userService.getUserByEmail(email);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String id,
            @RequestBody UserDTO userDTO,
            @RequestHeader("Authorization") String authHeader
    ) {
        UserDTO updated = userService.updateUser(id, userDTO, authHeader);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
            @RequestParam String email,
            @RequestHeader("Authorization") String authHeader
    ) {
        userService.deleteUserByEmail(email, authHeader);
        return ResponseEntity.noContent().build();
    }


}
