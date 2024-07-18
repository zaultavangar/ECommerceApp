package com.zaultavangar.ecommerce.kafka;

import com.zaultavangar.ecommerce.customer.CustomerResponse;
import com.zaultavangar.ecommerce.order.PaymentMethod;
import com.zaultavangar.ecommerce.product.PurchaseResponse;
import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products
) {

}
