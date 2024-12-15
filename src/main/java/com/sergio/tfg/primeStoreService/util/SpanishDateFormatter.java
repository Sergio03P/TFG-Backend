package com.sergio.tfg.primeStoreService.util;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class SpanishDateFormatter {
  private LocalDate date;

  public String formate(LocalDate today){
    date = today;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return today.format(formatter);
  }

  public String fullDateFormat(LocalDate date){
    //Configura el idioma español para el formato
    Locale spanishLocale = new Locale("es", "ES");
    //Define el formato deseado (dd/MM/yyyy HH:mm:ss)
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", spanishLocale);
    //obtiene la fecha y hora actual
    LocalDateTime currentlyDate = LocalDateTime.now();
    //formatea la fecha y hora en español
    return currentlyDate.format(formatter);
  }
}
