package com.sergio.tfg.primeStoreService.service.impl;

import io.getstream.chat.java.models.User;
import io.getstream.chat.java.services.framework.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class StreamChatServiceImpl {

  private final Client client;

  public StreamChatServiceImpl() {
    // Inicializa Client con las claves de API proporcionadas
    System.setProperty("STREAM_KEY", "${STREAM_KEY}");
    System.setProperty("STREAM_SECRET", "${STREAM_SECRET}");
    this.client = Client.getInstance();
  }

  public String generateToken(String username) {
    try {
      // Genera un token JWT específico para el usuario
      return User.createToken(username, null, new Date());
    } catch (Exception e) {
      throw new RuntimeException("Error al generar el token para el usuario: " + username, e);
    }
  }
}
