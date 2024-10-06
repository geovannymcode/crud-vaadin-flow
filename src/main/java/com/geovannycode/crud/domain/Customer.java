package com.geovannycode.crud.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Customer {
    private Long id;
    @NotBlank(message = "Se requiere nombre del cliente")
    private String name;
    @NotBlank(message = "Se requiere el correo electrónico del cliente")
    @Email(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "Ingresar el email valido.")
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
}
