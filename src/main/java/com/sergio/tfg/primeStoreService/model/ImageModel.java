package com.sergio.tfg.primeStoreService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
@Table(name = "images")
/**
 * Representan las imágenes que están asociadas a un producto en concreto
 */
public class ImageModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  @ToString.Exclude
  private ProductModel productModel; //referencia al producto al que pertenece
  private String image; //URL de la imagen
}
