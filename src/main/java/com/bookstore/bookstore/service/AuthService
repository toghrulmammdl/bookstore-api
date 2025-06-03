package com.bookstore.bookstore.service;

import com.bookstore.bookstore.dto.request.RegisterRequestDto;
import com.bookstore.bookstore.dto.response.RegisterResponseDto;
import com.bookstore.bookstore.entity.User;
import com.bookstore.bookstore.enums.RegistrationMethod;
import com.bookstore.bookstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponseDto registerResponseDto(RegisterRequestDto request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Not Registered: Password, Email or Phone Number is incorrect or already taken");
        }
        if(request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty() &&             userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()){
            System.out.println("Salam");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegistrationMethod(RegistrationMethod.REGISTRATION);
        user.setActive(false);

        String verificationToken = UUID.randomUUID().toString();
        user.setVerificationToken(verificationToken);
        user.setTokenExpiryDate(LocalDateTime.now().plusHours(1));
        User savedUser = userRepository.save(user);

        System.out.println("Verification url: http://localhost:8080/api/v1/auth/verify?token=" + verificationToken);

        return new RegisterResponseDto("Registration Success", null);
    }

    @Transactional
    public RegisterResponseDto verifyEmail(String token){
        User user = userRepository.findByVerificationToken(token).orElseThrow(()-> new RuntimeException("Wrong token"));

        if(user.getTokenExpiryDate() == null || user.getTokenExpiryDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Expired token");
        }

        user.setActive(true);
        user.setVerificationToken(null);
        user.setTokenExpiryDate(null);
        userRepository.save(user);

        return new RegisterResponseDto("Successfully confirmed email.", null);
    }
}

