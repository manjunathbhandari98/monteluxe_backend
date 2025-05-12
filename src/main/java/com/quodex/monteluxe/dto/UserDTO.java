package com.quodex.monteluxe.dto;

import com.quodex.monteluxe.util.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    @NotBlank(message = "Name Required")
    private String name;
    @NotBlank(message = "Email Required")
    @Email(message = "Invalid Email Format")
    private String email;
    private String phone;
    private Role role;
    private boolean active;
    private LocalDateTime createdAt;

}
