package com.sergio.tfg.primeStoreService.config;

import com.sergio.tfg.primeStoreService.model.*;
import com.sergio.tfg.primeStoreService.model.dto.*;
import com.sergio.tfg.primeStoreService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
  private final UserRepository userRepository;
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider  = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByUsername(username) //declara con que credencial quiero autenticar al usuario
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  //MAPPER
  @Bean
  public ModelMapper modelMapper(){
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    modelMapper.createTypeMap(UserModel.class, UserModelDTO.class);

    modelMapper.createTypeMap(ProductModel.class, ProductModelDTO.class)
            .addMappings(mapper -> mapper.map(
                    product -> {
                      if (product.getImages() != null) {
                        return product.getImages().stream()
                                .map(ImageModel::getImage) // Obtener solo la URL de la imagen
                                .collect(Collectors.toList());
                      }
                      return Collections.emptyList();
                    },
                    ProductModelDTO::setImages
            )).addMappings(mapper -> mapper.map(
                    productModel -> productModel.getUser().getId(),
                    (ProductModelDTO::setOwnerUserId)
            ));

    modelMapper.createTypeMap(ProductModelDTO.class, ProductModel.class)
            .addMappings(mapper -> mapper.map(productModelDTO -> {
              if (productModelDTO.getImages() != null) {
                return productModelDTO.getImages().stream()
                        .map(imageUrl -> {
                          System.out.println("Mapping back from URL: " + imageUrl);
                          return new ImageModel(null, null, imageUrl);
                        }) // Crear un ImageModel solo con la URL
                        .collect(Collectors.toList());
              }
              return Collections.emptyList(); // Devolver una lista vacía si no hay imágenes
            }, ProductModel::setImages));



    modelMapper.typeMap(ImageModel.class, ImageModelDTO.class);
    modelMapper.typeMap(ImageModelDTO.class, ImageModel.class);

    modelMapper.typeMap(TransactionModel.class, TransactionModelDTO.class)
                    .addMappings(mapper -> mapper.map(transactionModel -> transactionModel.getSeller().getId(), TransactionModelDTO::setSellerId))
                    .addMappings(mapper -> mapper.map(transactionModel -> transactionModel.getBuyer().getId(),TransactionModelDTO:: setBuyerId))
                    .addMappings(mapper -> mapper.map(transactionModel -> transactionModel.getProduct().getId(),TransactionModelDTO:: setProductId));
    modelMapper.typeMap(TransactionModelDTO.class, TransactionModel.class);

    modelMapper.typeMap(WalletModel.class, WalletModelDTO.class)
            .addMappings(mapper -> mapper.map(walletModel -> walletModel.getUserModel().getId(), WalletModelDTO::setUserModelId));

    modelMapper.typeMap(WalletModelDTO.class, WalletModel.class);

    modelMapper.typeMap(FeeModel.class, FeeModelDTO.class);
    modelMapper.typeMap(FeeModelDTO.class, FeeModel.class);

    modelMapper.typeMap(TransactionFeeModel.class, TransactionFeeModelDTO.class)
            .addMappings(mapper -> mapper.map(transactionFeeModel -> transactionFeeModel.getTransaction().getId(), TransactionFeeModelDTO::setTransactionId))
            .addMappings(mapper -> mapper.map(transactionFeeModel -> transactionFeeModel.getFee().getId(), TransactionFeeModelDTO::setFeeId));
    //modelMapper.typeMap(FeeModelDTO.class, FeeModel.class);

    //ReviewModel
    modelMapper.typeMap(ReviewModelDTO.class, ReviewModel.class);
    modelMapper.typeMap(ReviewModel.class, ReviewModelDTO.class);

    return modelMapper;
  }
}
