package com.zaultavangar.ecommerce.orderline;

public record OrderLineRequest(
    Integer id,
    Integer orderId,
    Integer productId,
    Double quantity
) {

}
