package com.example.bookhub.controller;

import com.example.bookhub.dto.AuthRequestDTO;
import com.example.bookhub.dto.AuthResponseDTO;
import com.example.bookhub.dto.RegisterRequestDTO;
import com.example.bookhub.entity.AppUser;
import com.example.bookhub.jwt.JwtService;
import com.example.bookhub.repository.AppUserRepository;
import com.example.bookhub.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO req) {
        AppUser u = new AppUser();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(u);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }
}
