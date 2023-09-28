package com.cielo.clients_pre_register.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record ClientPjDto(

      @NotBlank(message = "Field cnpj must not be blank.")
      @Length(min = 14, max = 14, message = "CNPJ must have 14 characters.")
      @CNPJ
      String cnpj,

      @NotBlank(message = "Field corporate reason must not be blank.")
      @Length(max = 50, message = "Corporate reason can have a maximum of 50 characters.")
      String corporate_reason,

      @NotNull(message = "Field MCC must not be null.")
      int mcc,

      @NotBlank(message = "Field CPF must not be blank.")
      @CPF(message = "Field CPF must be a valid format.")
      @Length(min = 11, max = 11, message = "CPF must have 11 characters.")
      String contact_cpf,

      @NotBlank(message = "Field name must not be blank.")
      @Length(min = 5, max = 50, message = "Name must be between 5 and 50 characters.")
      String contact_name,

      @NotBlank(message = "Field email must not be blank.")
      @Email(message = "Email must be a valid format.")
      @Pattern(regexp = "([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",message = "Email bust be a valid format.")
      String contact_email


) {



}
