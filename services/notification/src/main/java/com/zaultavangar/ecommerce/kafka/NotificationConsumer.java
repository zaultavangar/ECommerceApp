package com.zaultavangar.ecommerce.kafka;

import com.zaultavangar.ecommerce.email.EmailService;
import com.zaultavangar.ecommerce.kafka.order.OrderConfirmation;
import com.zaultavangar.ecommerce.kafka.payment.PaymentConfirmation;
import com.zaultavangar.ecommerce.notification.Notification;
import com.zaultavangar.ecommerce.notification.NotificationRepository;
import com.zaultavangar.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
  private final NotificationRepository repository;
   private final EmailService emailService;

  @KafkaListener(topics = "payment-topic")
  public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation)
      throws MessagingException {
    log.info(String.format("Consuming the message from payment topic Topic :: %s", paymentConfirmation));
    repository.save(
        Notification.builder()
            .type(NotificationType.PAYMENT_CONFIRMATION)
            .notificationDate(LocalDateTime.now())
            .paymentConfirmation(paymentConfirmation)
            .build()
    );

    String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();

    emailService.sendPaymentSuccessEmail(
        paymentConfirmation.customerEmail(),
        customerName,
        paymentConfirmation.amount(),
        paymentConfirmation.orderReference()
    );

  }

  @KafkaListener(topics = "order-topic")
  public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation)
      throws MessagingException {
    log.info(String.format("Consuming the message from order topic Topic :: %s", orderConfirmation));
    repository.save(
        Notification.builder()
            .type(NotificationType.PAYMENT_CONFIRMATION)
            .notificationDate(LocalDateTime.now())
            .orderConfirmation(orderConfirmation)
            .build()
    );

    String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();

    emailService.sendOrderConfirmationEmail(
        orderConfirmation.customer().email(),
        customerName,
        orderConfirmation.totalAmount(),
        orderConfirmation.orderReference(),
        orderConfirmation.products()
    );
  }
}
