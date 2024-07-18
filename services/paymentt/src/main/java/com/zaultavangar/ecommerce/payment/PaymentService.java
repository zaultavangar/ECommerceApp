package com.zaultavangar.ecommerce.payment;

import com.zaultavangar.ecommerce.notification.NotificationProducer;
import com.zaultavangar.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper paymentMapper;
  private final NotificationProducer notificationProducer;

  public Integer createPayment(PaymentRequest request) {
    var payment = paymentRepository.save(paymentMapper.toPayment(request));

    // Send notification to notification microservice
    notificationProducer.sendNotification(
        new PaymentNotificationRequest(
            request.orderReference(),
            request.amount(),
            request.paymentMethod(),
            request.customer().firstName(),
            request.customer().lastName(),
            request.customer().email()
        )
    );

    return payment.getId();


  }
}
