package com.catalogue.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegistrationRequest(
        @NotBlank(message = "First Name cannot be blank") String firstName,
        @NotBlank(message = "Last Name cannot be blank") String lastName,
        @NotBlank(message = "Address 1 cannot be blank") String address1,
        @NotBlank(message = "Address 2 cannot be blank") String address2,
        @NotBlank(message = "City cannot be blank") String city,
        @NotBlank(message = "Postcode cannot be blank") String postcode,
        @NotBlank(message = "phoneNumber cannot be blank") 
        @Pattern(regexp = "^\\d{9,15}$", message = "phoneNumber must minimum of o digit number") String phoneNumber,
        @NotBlank(message = "Email cannot be blank") 
        @Email(message = "Must include @,. and top level domain (.com, .uk etc) ", 
        regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]"
                + "+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$") String email,
        @NotBlank(message = "Password cannot be blank") String password,
        @NotBlank(message = "Role cannot be blank") String role

) {
}
