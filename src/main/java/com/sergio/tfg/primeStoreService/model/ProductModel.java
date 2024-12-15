package com.sergio.tfg.primeStoreService.model;

import com.sergio.tfg.primeStoreService.model.enums.productEnums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "products")
/**
 * Representan los productos que se suben a la aplicación
 */
public class ProductModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //genera automáticamente un id al crear un nuevo registro
  private Integer id;
  private String name; //nombre del producto
  private String description; //descripción del producto
  private double price; //precio del producto
  private String date; //fecha de subida
  @Enumerated(EnumType.STRING)
  private Category category; //categoría a la que pertenece

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  private UserModel user; //usuario al quee pertenece el producto
  @OneToMany(mappedBy = "productModel", cascade = CascadeType.ALL, orphanRemoval = true)
  //@ToString.Exclude
  private List<ImageModel> images; //lista de imágenes del producto
  @ToString.Exclude
  @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private TransactionModel transaction; //transacción asociada al producto, si existe
}
