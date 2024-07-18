package com.zaultavangar.ecommerce.notification;

import com.zaultavangar.ecommerce.payment.PaymentMethod;
import java.math.BigDecimal;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
