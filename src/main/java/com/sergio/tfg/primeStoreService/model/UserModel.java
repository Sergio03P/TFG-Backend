package com.sergio.tfg.primeStoreService.model;

import com.sergio.tfg.primeStoreService.model.enums.userEnums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
/**
 * Name es el nombre de la tabla que se va a crear en la BBDD y uniqueConstraints indica que los valores de esa columnas han de ser
 * únicos
 */
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
/**
 * Esta clase representa al usuario dentro d la aplicación
 */
public class UserModel implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @Column(nullable = false) //no se puede hacer el insert en la tabla si la columna username es nulo
  String username; //nombre del usuario
  String email; //correo
  String password; //contraseña
  String name; //nombre real del usuario
  String firstname; //primer apellido
  String lastname; //segundo apellido
  int postalCode; //código postal
  @Enumerated(EnumType.STRING)
  Role role; //rol del usuario que maneja spring
  String registerDate; //fecha de registro del usuario en la app
  String profilePicture;
  /**
   * /mappedBy, asigna el nombre de la tabla con la que se relaciona
   * CascadeType.ALL indica que cualquier operación en la tabla de usuarios se propaga a la tabla de productos
   * orphanRemoval, hace que si se elimina un producto de usuario también se elimina  de la tabla producto
   */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductModel> products; //lista de productos en venta del usuario

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "favourite_user_products",
          joinColumns = @JoinColumn(name = "user"),
          inverseJoinColumns = @JoinColumn(name = "products")
  )
  private List<ProductModel> favouriteProductsModelList = new ArrayList<>(); //lista de productos en favoritos

  @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true) //debe coincidir con el nombre del atributo en la entidad contraria
  private Set<TransactionModel> sellerTransaction = new HashSet<>();

  @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true) //debe coincidir con el nombre del atributo en la entidad contraria
  private Set<TransactionModel> buyerTransaction = new HashSet<>();

  @OneToOne(mappedBy = "userModel", cascade = CascadeType.ALL, orphanRemoval = true)
  private WalletModel walletModel;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name())); //devuelve una lista de autoridad del usuario autenticado
  }

  //En los siguientes métodos devolveremos true porque van a controlarse en los filtros

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
