package com.sergio.tfg.primeStoreService.config;

import com.sergio.tfg.primeStoreService.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Declara la clase como configuración, indicando la existencia de métodos marcados como bean que crea y configura los objetos requeridos
@EnableWebSecurity
@RequiredArgsConstructor
/**
 * Clase encargada de actura como filtro de seguridad para las peticiones recibidas a través de los endpoints
 */
public class SecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      //devuelve la petición http siempre y cuando pase por los filtros requeridos
        return http
                //desabilita la necesidad de incluir un token crsf para las peticiones post
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        //permite todas las peticiones http que contengan la siguiente ruta indicada
                        authRequest.requestMatchers("/auth/**").permitAll()
                                   .anyRequest().authenticated()) //cualquier otra ruta debe ser autenticada
                .sessionManagement(sessionManager ->
                        //inhabilita la creación de sesiones
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider) //se indica el proveedor a utilizar
                //indica el filtro que debe ejecutarse previamente
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
