package com.sergio.tfg.primeStoreService.model.dto;

import com.sergio.tfg.primeStoreService.model.enums.transactionEnums.DeliveryMethod;
import com.sergio.tfg.primeStoreService.model.enums.transactionEnums.PayMethod;
import com.sergio.tfg.primeStoreService.model.enums.transactionEnums.TransactionState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModelDTO {
  private Integer id;
  private String date;
  private double total_price;
  private TransactionState transactionState;
  private PayMethod payMethod;
  private DeliveryMethod deliveryMethod;

  private Integer sellerId;
  private Integer buyerId;
  private Integer productId;

  private List<FeeModelDTO> fees;
  private ReviewModelDTO review;
}
