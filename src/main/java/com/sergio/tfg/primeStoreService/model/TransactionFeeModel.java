package com.sergio.tfg.primeStoreService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_fees")
/**
 * Es la tabla resultante de la relacion N:M de la tabla "transaction" y "fees", y esque las transacciones
 * tienen una lista de tasas y esas tasas se encuentran en todos las transacciones
 */
public class TransactionFeeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // Clave primaria única de la tabla intermedia

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "transaction_id", nullable = false)
  private TransactionModel transaction; //clave foranea de la tabla transaction

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fee_id", nullable = false)
  private FeeModel fee; //clave foranea de la tabla fee
}