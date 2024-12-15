package com.sergio.tfg.primeStoreService.service.impl;

import com.sergio.tfg.primeStoreService.exception.CustomException;
import com.sergio.tfg.primeStoreService.model.UserModel;
import com.sergio.tfg.primeStoreService.model.dto.UserModelDTO;
import com.sergio.tfg.primeStoreService.repository.UserRepository;
import com.sergio.tfg.primeStoreService.service.UserService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private Mapper mapper;

  @Override
  public Optional<UserModelDTO> findUserById(Integer id) {
    Optional<UserModel> userModel = userRepository.findById(id);
    return userModel.map(user -> mapper.toUserModelDTO(user));
  }

  @Override
  public Optional<UserModelDTO> findByEmail(String email) {
    Optional<UserModel> userModel = userRepository.findByEmail(email);
    return userModel.map(user -> mapper.toUserModelDTO(user));
  }

  @Override
  public Optional<UserModelDTO> findByUsername(String username) {
    Optional<UserModel> userModel = userRepository.findByUsername(username);
    return userModel.map(user -> mapper.toUserModelDTO(user));
  }

  @Override
  public UserModelDTO updateUser(UserModelDTO userModelDTO) {
    // Buscar el usuario existente en la base de datos
    UserModel existingUser = userRepository.findById(userModelDTO.getId())
            .orElseThrow(() -> new CustomException("Usuario no encontrado con ID: " + userModelDTO.getId()));

    // Actualizar solo los campos permitidos del usuario existente
    existingUser.setUsername(userModelDTO.getUsername());
    existingUser.setEmail(userModelDTO.getEmail());
    existingUser.setName(userModelDTO.getName());
    existingUser.setFirstname(userModelDTO.getFirstname());
    existingUser.setLastname(userModelDTO.getLastname());
    existingUser.setPostalCode(userModelDTO.getPostalCode());
    existingUser.setProfilePicture(userModelDTO.getProfilePicture());

    // Guardar los cambios en el repositorio
    UserModel updatedUser = userRepository.save(existingUser);

    // Convertir el modelo actualizado a DTO y lo devolvemos
    return mapper.toUserModelDTO(updatedUser);
  }
}
