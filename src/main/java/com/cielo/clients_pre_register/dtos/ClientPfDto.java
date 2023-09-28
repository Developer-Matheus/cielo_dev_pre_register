package com.cielo.clients_pre_register.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record ClientPfDto(
      @NotBlank(message = "Field name must not be blank.")
      @Length(min = 5, max = 50, message = "Name must be between 5 and 50 characters.")
      String name,
      @NotBlank(message = "Field email must not be blank.")
      @Email(message = "Email must be a valid format.")
      @Pattern(regexp = "([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",message = "Email bust be a valid format.")
      String email,
      @NotNull(message = "Field MCC must not be null.")
      int mcc,
      @NotBlank(message = "Field CPF must not be blank.")
      @CPF(message = "Field CPF must be a valid format.")
      @Length(min = 11, max = 11, message = "CPF must have 11 characters.")
      String cpf
) {



}
