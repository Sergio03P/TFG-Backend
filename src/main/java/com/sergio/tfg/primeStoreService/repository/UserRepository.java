package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/**
 * Interfaz que interactúa con la BBDD utilizando JPA Repository
 */
public interface UserRepository extends JpaRepository<UserModel, Integer> {
  //consulta personalizada que busca registros en la BBDD en la columna username de la tabla user
  Optional<UserModel> findByUsername(String username);
  Optional<UserModel> findByEmail(String email); //buscas registros coincidentes en la columna email de la tabla user
}
