package com.sergio.tfg.primeStoreService.model.dto;

import com.sergio.tfg.primeStoreService.model.enums.userEnums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModelDTO {
  private Integer id;
  private String username;
  private String email;
  private String name;
  private String firstname;
  private String lastname;
  private int postalCode;
  private Role role;
  private String registerDate;
  private String profilePicture;
}
