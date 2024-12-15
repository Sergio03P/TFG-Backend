package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.FeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<FeeModel, Integer> {
  FeeModel findByName(String name);
}
