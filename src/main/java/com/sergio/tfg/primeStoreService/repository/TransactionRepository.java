package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
  @Query("SELECT t FROM TransactionModel t WHERE t.seller.id = :userId OR t.buyer.id = :userId")
  List<TransactionModel> getAllTransactionsFromUserId(@Param("userId") Integer userId);

  TransactionModel findByProductId(Integer id);
}
