package com.sergio.tfg.primeStoreService.auth;

import com.sergio.tfg.primeStoreService.model.enums.userEnums.Role;
import com.sergio.tfg.primeStoreService.model.UserModel;
import com.sergio.tfg.primeStoreService.repository.UserRepository;
import com.sergio.tfg.primeStoreService.auth.jwt.JwtService;
import com.sergio.tfg.primeStoreService.service.impl.StreamChatServiceImpl;
import com.sergio.tfg.primeStoreService.util.SpanishDateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final StreamChatServiceImpl streamChatService;
  private final SpanishDateFormatter spanishDateFormatter;

  /**
   * Método que autentica al usuario y genera un token para encapsular los datos del usuario y utilizarlo en endpoints privados
   * @param request contiene el nombre de usuario y la contraseña del usuario que quiere autenticarse
   * @return AuthResponse, devuelve el token generado para el usuario que se ha autenticado si las credenciales son correctas
   */
  public AuthResponse login(LoginRequest request) {
    //se utiliza el método authenticate para autenticar al usuario usando sus credenciales(usuario y contraseña), si no es capaz de autenticarlo lanzará una excepcón
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow(); //se busca en la BBDD usando el nombre de usuario y se guarda el objeto de usuario
    String token = jwtService.getToken(user); //se crea un token para encapsular los datos del usuario y utilizarlo para identificarlo en futuras peticiones
    return AuthResponse.builder()  //Se construye una respuesta de autenticación que develve el token del usuario autenticado
            .token(token)
            .chatToken(streamChatService.generateToken(user.getUsername()))
            .build();
  }

  /**
   * Método que crea un nuevo objeto de usuario y lo inserta en la BBDD, posteriormente genera el token de este nuevo usuario
   * @param request datos del usuario a almacenar en la base de datos
   * @return AuthResponse, devuelve el token del usuario si se ha podido registrar correctamente
   */
  public AuthResponse register(RegisterRequest request) {
    UserModel user = UserModel.builder() //Se crea un nuevo objeto de usuario con las datos del mismo provenientes del request
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .name(request.getName())
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .postalCode(request.getPostalCode())
            .role(Role.USER)
            .registerDate(spanishDateFormatter.formate(LocalDate.now()))
            .profilePicture(request.getProfilePicture())
            .build();

    userRepository.save(user);//se guarda al usuario en la base de datos

    return AuthResponse.builder() //construye una respuesta que devuelve el token creado para el nuevo usuario
            .token(jwtService.getToken(user))
            .build();
  }
}
