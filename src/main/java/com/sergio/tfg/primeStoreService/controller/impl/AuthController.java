package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.auth.AuthResponse;
import com.sergio.tfg.primeStoreService.auth.AuthService;
import com.sergio.tfg.primeStoreService.auth.LoginRequest;
import com.sergio.tfg.primeStoreService.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
/**
 * Controlador que mapea las peticiones públicas relacionadas con la autenticación del usuario ya se como
 * registro o inicio de sesión
 */
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login")//endpoint público de inicio de sesión
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")//endpoint público de registro
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

}
