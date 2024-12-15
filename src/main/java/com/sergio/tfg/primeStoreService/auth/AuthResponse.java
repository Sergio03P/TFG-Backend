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
 * Esta clase representa la respuesta de autorización indipendientemente de si se trata de un registro o de un inicio de sesión,
 * y devuelve el token necesario del usuario, en este caso no solo devolvemos el token de autorización de los endpoints
 * privados si no que también devolvemos el token del usuario referente al servicio de chat dentro de la aplicación
 */
public class AuthResponse {
  String token; //token de autorización de los endpoints privados
  String chatToken; //token de autorización para el chat con otros usuarios dentro de la app
}
