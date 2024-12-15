package com.sergio.tfg.primeStoreService.controller.impl;

import com.sergio.tfg.primeStoreService.controller.UserController;
import com.sergio.tfg.primeStoreService.model.dto.UserModelDTO;
import com.sergio.tfg.primeStoreService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserControllerImpl implements UserController {
  @Autowired
  private UserService userService;

  @Override
  @GetMapping("/getUser/{id}")
  public Optional<UserModelDTO> getUserById(@PathVariable Integer id) {
    return userService.findUserById(id);
  }

  @Override
  @GetMapping("/getUserByEmail/{email}")
  public Optional<UserModelDTO> findByEmail(@PathVariable String email) {
    return userService.findByEmail(email);
  }

  @Override
  @GetMapping("/getUserByUsername/{username}")
  public Optional<UserModelDTO> findByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @Override
  @PutMapping("/updateUser")
  public UserModelDTO updateUser(@RequestBody UserModelDTO userModelDTO) {
    return userService.updateUser(userModelDTO);
  }
}
