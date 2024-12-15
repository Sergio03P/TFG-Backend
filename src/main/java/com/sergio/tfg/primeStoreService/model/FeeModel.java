package com.sergio.tfg.primeStoreService.model;

import com.sergio.tfg.primeStoreService.model.enums.feeEnums.FeeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Esta clase representa las tasas que se le aplican a los productos cuando se realiza una compra
 */
@Table(name = "fees", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class FeeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name; //nombre de la tasa
  @Enumerated(EnumType.STRING)
  private FeeType feeType; //tipo de tasa
  private double amount; //cantidad de la tasa

  @ToString.Exclude
  @OneToMany(mappedBy = "fee", fetch = FetchType.LAZY)
  private List<TransactionFeeModel> transactionFees = new ArrayList<>(); //lista de transacciones a las que se aplica
}
