package com.jmfactura.jmfactura.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jmfactura.jmfactura.jwt.JwtService;
import com.jmfactura.jmfactura.models.user.Role;
import com.jmfactura.jmfactura.models.user.User;
import com.jmfactura.jmfactura.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).userName(user.getUsername())
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // contraseña sin encryptar
                                                                         // request.getPassword()
                .firstname(request.getFirstname())
                .lastname(request.getLasname())
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}
