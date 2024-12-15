package com.sergio.tfg.primeStoreService.repository;

import com.sergio.tfg.primeStoreService.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
  @Query("SELECT r FROM ReviewModel r WHERE r.transaction.id = :transactionId")
  List<ReviewModel> findReviewsByTransactionId(@Param("transactionId") Integer transactionId);
}
