package com.catalogue.authentication.dto;

public record UseDetailsResponse(
        String firstName,
        String lastName,
        String address1,
        String address2,
        String city,
        String postcode,
        String phoneNumber,
        String email,
        String role) {
}
