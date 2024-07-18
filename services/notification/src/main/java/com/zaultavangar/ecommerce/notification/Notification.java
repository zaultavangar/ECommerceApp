package com.zaultavangar.ecommerce.notification;

import com.zaultavangar.ecommerce.kafka.order.OrderConfirmation;
import com.zaultavangar.ecommerce.kafka.payment.PaymentConfirmation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
  @Id
  private String id;
  private NotificationType type;
  private LocalDateTime notificationDate;
  private OrderConfirmation orderConfirmation;
  private PaymentConfirmation paymentConfirmation;
}
