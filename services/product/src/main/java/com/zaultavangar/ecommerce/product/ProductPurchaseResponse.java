package com.zaultavangar.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
    Integer productId,
    String name,
    String description,
    BigDecimal price,
    Double quantity
) {

}
