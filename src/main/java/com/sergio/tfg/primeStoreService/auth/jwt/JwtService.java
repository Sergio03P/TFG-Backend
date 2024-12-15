package com.sergio.tfg.primeStoreService.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {
  //clave secreta utilizada para la firma del token
  private static final String SECRET_KEY = "556341635463N46456213144157869967M23142315S";
  public String getToken(UserDetails user) {
    return getToken(new HashMap<>(), user);
  }

  /**
   * Método para crear el token
   * @param extraClaims especificaciones adicionales del token
   * @param user usuario al cual se le va a atribuir el token
   * @return String token del usuario
   */
  private String getToken(HashMap<String,Object> extraClaims, UserDetails user) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis())) //aqui falta el tiempo de expiración
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();//crea el objeto y lo serializa
  }

  /**
   * Método utilizado poara obtener la key para la firma del token
   * @return Key
   */
  private Key getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);//decodifica la clave secreta en bytes
    return Keys.hmacShaKeyFor(keyBytes);//crea una nueva instancia de la secret key
  }

  public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    //return (username.equals(userDetails.getUsername()) && !istokenExpired(token));
    return (username.equals(userDetails.getUsername()));
  }

  private Claims getAllClaims(String token){
    return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date getExpiration(String token){
    return getClaim(token, Claims::getExpiration);
  }

  private boolean istokenExpired(String token){
    return getExpiration(token).before(new Date());
  }
}
