package com.sergio.tfg.primeStoreService.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
/**
 * Clase que se encarga de filtrar la petición http con relación al JWT,
 * al extender de la clase OncePerRequestFilter, indicamos que el filtro solo se ejecute
 * una vez por petición
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  /**
   * Método que intercepta las solicitudes recibidas y se encarga de la verificación del token
   * @param request
   * @param response
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String token = getTokenFromRequest(request); //extrae el token JWT del encabezado Authorization de la solicitud http
    final String username;

    if(token == null){
      filterChain.doFilter(request, response); //pasa el control de la solicitud al siguiente filtro de la cadena si el token es nulo
      return;
    }

    username = jwtService.getUsernameFromToken(token); //si el token no es nulo se obtiene el nombre de usuario del token
    if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){ //si el usuario no está autenticado en el contexto de seguridad de spring, procede con la validación
      UserDetails userDetails = userDetailsService.loadUserByUsername(username); //carga los detalles del usuario a partir del nombre de usuario extraído del token
      if(jwtService.isTokenValid(token, userDetails)){ //verifica si el token es válido
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //representa la autenticación del usuario
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //establece los detalles de la autenticación
        SecurityContextHolder.getContext().setAuthentication(authToken); //establece la autenticación en el contexto de seguridad de Spring
      }
    }
    filterChain.doFilter(request, response); //continua con el procesamiento de la solicitud

  }

  /**
   * Método que obtiene el token de la petición realizada
   * @param request petición
   * @return String token
   */
  private String getTokenFromRequest(HttpServletRequest request) {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); //obtiene el encabezado de autorización de la petición
    //si la cabecera de la autorización comienza con "Bearer ", sabemos que detrás le digue el token
    if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
      return authHeader.substring(7); //obtiene el token
    }
    return null;
  }
}
