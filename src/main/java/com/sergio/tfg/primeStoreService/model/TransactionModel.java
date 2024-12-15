package com.sergio.tfg.primeStoreService.model;

import com.sergio.tfg.primeStoreService.model.enums.transactionEnums.DeliveryMethod;
import com.sergio.tfg.primeStoreService.model.enums.transactionEnums.PayMethod;
import com.sergio.tfg.primeStoreService.model.enums.transactionEnums.TransactionState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
/**
 * Representan las transacciones cuando un usuario compra un producto dentro de la aplicación
 */
public class TransactionModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String date; //fecha de inicio de la transacción
  private double total_price; //precio final de la transacción con la suma de las tasas
  @Enumerated(EnumType.STRING)
  private TransactionState transactionState; //estado de la transacción
  @Enumerated(EnumType.STRING)
  private PayMethod payMethod; //método de pago
  @Enumerated(EnumType.STRING)
  private DeliveryMethod deliveryMethod; //método de envío, ahora mismo siempre será en persona

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seller_id")
  private UserModel seller; //referencia al usuario vendedor del producto
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "buyer_id")
  private UserModel buyer; //referencia al usuario que compra el producto
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private ProductModel product; //referencia al objeto que estoy comprando
  @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TransactionFeeModel> transactionFees = new ArrayList<>(); //listas de transacción con sus tasas
  @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true) //no estaba el orphan
  @JoinColumn(name = "review_id")
  private ReviewModel review; //reseña del comprador

}

