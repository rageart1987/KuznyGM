package com.example.registrationlogindemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto
{
    private Long id;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String city;

    @NotEmpty
    private String subdivision;

    private String region;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

}
