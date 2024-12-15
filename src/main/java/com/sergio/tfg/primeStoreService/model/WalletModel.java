package com.sergio.tfg.primeStoreService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
/**
 * Representa el monedero virtual de la aplicación que se asocia a un usuario y con el cual,
 * puede comprar productos dentro de la aplicación
 */
public class WalletModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private double amount; //saldo del monedero

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id")
  private UserModel userModel; //referencia la usuario al que se le asocia el monedero

}
