package com.sergio.tfg.primeStoreService.controller;

import com.sergio.tfg.primeStoreService.model.dto.UserModelDTO;

import java.util.Optional;

public interface UserController {
  public Optional<UserModelDTO> getUserById(Integer id);
  public Optional<UserModelDTO> findByEmail(String email);
  public Optional<UserModelDTO> findByUsername(String username);
  public UserModelDTO updateUser(UserModelDTO userModelDTO);
}
