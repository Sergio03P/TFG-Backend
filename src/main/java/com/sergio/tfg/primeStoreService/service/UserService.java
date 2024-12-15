package com.sergio.tfg.primeStoreService.service;

import com.sergio.tfg.primeStoreService.model.dto.UserModelDTO;
import java.util.Optional;

public interface UserService {
  public Optional<UserModelDTO> findUserById(Integer id);
  public Optional<UserModelDTO> findByEmail(String email);
  public Optional <UserModelDTO> findByUsername(String username);
  public UserModelDTO updateUser(UserModelDTO userModelDTO);
}
