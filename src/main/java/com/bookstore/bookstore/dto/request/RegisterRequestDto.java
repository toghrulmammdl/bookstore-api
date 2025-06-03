package com.bookstore.bookstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @Email(message = "Enter email")
    @NotBlank(message = "Email mustn't be blank")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "Password can't be empty")
    @Size(message = "Password must be longer than 7", min = 7)
    private String password;
}
