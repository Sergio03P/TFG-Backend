package com.sergio.tfg.primeStoreService.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Clase que contiene los datos del usuario cuando se realiza una petición de login dentro de la aplicación
 */
public class LoginRequest {
  String username; //nombre de usuario
  String password; //contraseña
}
