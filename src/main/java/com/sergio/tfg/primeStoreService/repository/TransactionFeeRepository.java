package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.TransactionFeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionFeeRepository extends JpaRepository<TransactionFeeModel, Integer> {
  @Query("SELECT tf FROM TransactionFeeModel tf WHERE tf.transaction.id = :transactionId")
  List<TransactionFeeModel> findByTransactionId(Integer transactionId);
}
