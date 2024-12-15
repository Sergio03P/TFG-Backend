package com.sergio.tfg.primeStoreService.service.impl;

import com.sergio.tfg.primeStoreService.exception.CustomException;
import com.sergio.tfg.primeStoreService.model.*;
import com.sergio.tfg.primeStoreService.model.dto.FeeModelDTO;
import com.sergio.tfg.primeStoreService.model.dto.TransactionModelDTO;
import com.sergio.tfg.primeStoreService.repository.*;
import com.sergio.tfg.primeStoreService.service.TransactionService;
import com.sergio.tfg.primeStoreService.util.Mapper;
import com.sergio.tfg.primeStoreService.util.SpanishDateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final ReviewRepository reviewRepository;
  private final SpanishDateFormatter spanishDateFormatter;

  @Autowired
  private final Mapper mapper;

  @Override
  public List<TransactionModelDTO> getAllTransactions() {
    return transactionRepository.findAll().stream().
            map((transactionModel -> mapper.toTransactionModelDTO(transactionModel))).collect(Collectors.toList());
  }

  @Override
  public List<TransactionModelDTO> getAllTransactionsFromUserId(Integer id) {
    List<TransactionModelDTO> list = transactionRepository.getAllTransactionsFromUserId(id).stream().map(transactionModel -> mapper.toTransactionModelDTO(transactionModel)).collect(Collectors.toList());
    for(TransactionModelDTO dto: list) {
      System.out.println(dto.toString());
    }
    return list;

  }

  @Override
  public TransactionModelDTO getTransactionByProductId(Integer id) {
    TransactionModel transactionModel = transactionRepository.findByProductId(id);
    if(transactionModel != null){
      return mapper.toTransactionModelDTO(transactionModel);
    }else{
      return null;
    }
  }

  @Override
  public TransactionModelDTO saveTransaction(TransactionModelDTO transactionModelDTO) {
    // Obtener los objetos seller, buyer y product
    UserModel sellerUser = userRepository.findById(transactionModelDTO.getSellerId())
            .orElseThrow(() -> new CustomException("No seller user found by id: " + transactionModelDTO.getSellerId()));

    UserModel buyerUser = userRepository.findById(transactionModelDTO.getBuyerId())
            .orElseThrow(() -> new CustomException("No buyer user found by id: " + transactionModelDTO.getBuyerId()));

    ProductModel product = productRepository.findById(transactionModelDTO.getProductId())
            .orElseThrow(() -> new CustomException("No product found by id: " + transactionModelDTO.getProductId()));

    // Crear el modelo de transacción a partir del DTO
    TransactionModel transactionModel = mapper.fromTransactionModelDTO(transactionModelDTO);
    transactionModel.setSeller(sellerUser);
    transactionModel.setBuyer(buyerUser);
    transactionModel.setProduct(product);
    transactionModel.setDate(spanishDateFormatter.fullDateFormat(LocalDate.now()));

    // Crear las tasas y configurar las relaciones
    List<TransactionFeeModel> transactionFees = new ArrayList<>();
    System.out.println("Obteniendo las fees");
    for (FeeModelDTO feeDTO : transactionModelDTO.getFees()) {
      FeeModel feeModel = mapper.fromFeeModelDTO(feeDTO);
      TransactionFeeModel transactionFeeModel = new TransactionFeeModel();
      transactionFeeModel.setTransaction(transactionModel); // Relacionar con la transacción
      transactionFeeModel.setFee(feeModel); // Relacionar con la tasa
      transactionFees.add(transactionFeeModel);
    }

    // Asignar las tasas a la transacción antes de guardar
    transactionModel.setTransactionFees(transactionFees);

    // Guardar la transacción junto con las tasas
    transactionModel = transactionRepository.save(transactionModel);

    // Manejar la reseña, si existe
    if (transactionModelDTO.getReview() != null) {
      ReviewModel reviewModel = mapper.fromReviewDTO(transactionModelDTO.getReview());
      reviewModel = reviewRepository.save(reviewModel); // Guardar la reseña

      // Asociar la reseña con la transacción
      reviewModel.setTransaction(transactionModel);
      transactionModel.setReview(reviewModel);

      // Actualizar la transacción con la reseña asociada
      transactionRepository.save(transactionModel);
    }

    // Retornar el DTO actualizado
    return mapper.toTransactionModelDTO(transactionModel);
  }

  @Override
  public TransactionModelDTO updateTransaction(TransactionModelDTO transactionModelDTO) {
    // Buscar la transacción existente
    TransactionModel existingTransaction = transactionRepository.findById(transactionModelDTO.getId())
            .orElseThrow(() -> new CustomException("Transaction not found by id: " + transactionModelDTO.getId()));

    // Actualizar campos básicos
    Optional.ofNullable(transactionModelDTO.getDate()).ifPresent(existingTransaction::setDate);
    if (transactionModelDTO.getTotal_price() != 0) {
      existingTransaction.setTotal_price(transactionModelDTO.getTotal_price());
    }
    Optional.ofNullable(transactionModelDTO.getTransactionState()).ifPresent(existingTransaction::setTransactionState);
    Optional.ofNullable(transactionModelDTO.getPayMethod()).ifPresent(existingTransaction::setPayMethod);
    Optional.ofNullable(transactionModelDTO.getDeliveryMethod()).ifPresent(existingTransaction::setDeliveryMethod);

    // Actualizar relaciones (seller, buyer, product)
    Optional.ofNullable(transactionModelDTO.getSellerId()).ifPresent(sellerId -> {
      UserModel seller = userRepository.findById(sellerId)
              .orElseThrow(() -> new CustomException("No seller user found by id: " + sellerId));
      existingTransaction.setSeller(seller);
    });

    Optional.ofNullable(transactionModelDTO.getBuyerId()).ifPresent(buyerId -> {
      UserModel buyer = userRepository.findById(buyerId)
              .orElseThrow(() -> new CustomException("No buyer user found by id: " + buyerId));
      existingTransaction.setBuyer(buyer);
    });

    Optional.ofNullable(transactionModelDTO.getProductId()).ifPresent(productId -> {
      ProductModel product = productRepository.findById(productId)
              .orElseThrow(() -> new CustomException("No product found by id: " + productId));
      existingTransaction.setProduct(product);
    });

    // Manejar reseña
    if (transactionModelDTO.getReview() != null) {
      ReviewModel updatedReview = mapper.fromReviewDTO(transactionModelDTO.getReview());
      updatedReview = reviewRepository.save(updatedReview); // Guardar la reseña primero
      System.out.println("updated review "+ updatedReview);
      existingTransaction.setReview(updatedReview);         // Asociar la reseña con la transacción
    };

    // Guardar la transacción actualizada
    TransactionModel updatedTransaction = transactionRepository.save(existingTransaction);
    return mapper.toTransactionModelDTO(updatedTransaction);
  }
}
