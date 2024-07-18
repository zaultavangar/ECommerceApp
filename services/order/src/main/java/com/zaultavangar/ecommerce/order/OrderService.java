package com.zaultavangar.ecommerce.order;

import com.zaultavangar.ecommerce.customer.CustomerClient;
import com.zaultavangar.ecommerce.exception.BusinessException;
import com.zaultavangar.ecommerce.kafka.OrderConfirmation;
import com.zaultavangar.ecommerce.kafka.OrderProducer;
import com.zaultavangar.ecommerce.orderline.OrderLineRequest;
import com.zaultavangar.ecommerce.orderline.OrderLineService;
import com.zaultavangar.ecommerce.payment.PaymentClient;
import com.zaultavangar.ecommerce.payment.PaymentRequest;
import com.zaultavangar.ecommerce.product.ProductClient;
import com.zaultavangar.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final CustomerClient customerClient;

  private final ProductClient productClient;

  private final OrderRepository orderRepository;

  private final OrderMapper orderMapper;

  private final OrderLineService orderLineService;

  private final OrderProducer orderProducer;

  private final PaymentClient paymentClient;

  public Integer createOrder(OrderRequest request) {
    // check the customer --> Open Feign
    var customer = this.customerClient.findCustomerById(request.customerId())
        .orElseThrow(() -> new BusinessException(
            "Cannot create order:: No customer exists with the provided ID" + request.customerId()));

    var purchasedProducts = this.productClient.purchaseProducts(request.products());   // purchase the products --> using the product microservice (Rest Template)

    var order = this.orderRepository.save(orderMapper.toOrder(request));   // persist order

    // persist order lines
    for (PurchaseRequest purchaseRequest: request.products()){
      orderLineService.saveOrderLine(
          new OrderLineRequest(
              null,
              order.getId(),
              purchaseRequest.productId(),
              purchaseRequest.quantity()
          )
      );
    }
    // Start payment process
    var paymentRequest = new PaymentRequest(
        request.amount(),
        request.paymentMethod(),
        order.getId(),
        order.getReference(),
        customer
    );
    paymentClient.requestOrderPayment(paymentRequest);

    // Send the order confirmation --> notification-ms (kafka)
    orderProducer.sendOrderConfirmation(
        new OrderConfirmation(
            request.reference(),
            request.amount(),
            request.paymentMethod(),
            customer,
            purchasedProducts
        )
    );

    return order.getId();
  }

  public List<OrderResponse> findAll() {
    return orderRepository.findAll()
        .stream()
        .map(orderMapper::fromOrder)
        .collect(Collectors.toList());
  }

  public OrderResponse findById(Integer orderId) {
    return orderRepository.findById(orderId)
        .map(orderMapper::fromOrder)
        .orElseThrow(() -> new EntityNotFoundException(String.format(
            "No order found with the provided ID:: %d", orderId)));
  }
}
