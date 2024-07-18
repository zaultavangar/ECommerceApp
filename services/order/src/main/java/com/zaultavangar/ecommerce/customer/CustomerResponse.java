package com.zaultavangar.ecommerce.customer;

public record CustomerResponse(
    String id,
    String firstName,
    String lastName,
    String email
) {

}
