package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletModel, Integer> {
}
