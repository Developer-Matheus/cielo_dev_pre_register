package com.cielo.clients_pre_register.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

public record ClientPjDto(

      @NotBlank(message = "Field cnpj must not be blank.")
      @Length(min = 14, max = 18, message = "CPF must be between 14 and 18 characters.")
      @CNPJ(message = "Field CNPJ must be a valid format.")
      String cnpj,

      @NotBlank(message = "Field corporate reason must not be blank.")
      @Length(max = 50, message = "Corporate reason can have a maximum of 50 characters.")
      String corporate_reason,

      @NotNull(message = "Field MCC must not be null.")
      @Min(value=1,message = "Field MCC must be greater than 0.")
      int mcc,

      @NotBlank(message = "Field CPF must not be blank.")
      @Length(min = 11, max = 14, message = "CPF must be between 11 and 14 characters.")
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
