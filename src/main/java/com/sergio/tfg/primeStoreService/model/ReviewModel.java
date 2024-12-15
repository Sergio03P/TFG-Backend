package com.sergio.tfg.primeStoreService.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
/**
 * Representa la reseña hacia el usuario vendedor cuando el comprador ha recibido el producto
 * y la transacción ha finalizado
 */
public class ReviewModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private double rate; //valoración del 0.0-5.0
  private String comment; //comentario del comprador al vendedor

  @ToString.Exclude
  @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
  private TransactionModel transaction; //transacción a la que se asocia el comentario
}