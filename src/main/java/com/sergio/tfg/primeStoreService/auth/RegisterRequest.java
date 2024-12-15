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
 * Clase que contiene los datos del usuario cuando se realiza una petición de registro dentro de la aplicación
 */
public class RegisterRequest {
  String username; //nombre de usuario
  String email; //correo
  String password; //contraseña
  String name; //nombre real del usuario
  String firstname; //primer apellido real del usuario
  String lastname; //segundo apellido real del usuario
  int postalCode; //código postal del usuario
  String profilePicture; //foto de perfil del usuario
}
