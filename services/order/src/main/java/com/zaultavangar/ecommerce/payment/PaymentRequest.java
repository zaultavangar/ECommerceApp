package com.zaultavangar.ecommerce.payment;

import com.zaultavangar.ecommerce.customer.CustomerResponse;
import com.zaultavangar.ecommerce.order.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {

}
