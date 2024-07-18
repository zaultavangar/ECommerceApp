package com.zaultavangar.ecommerce.orderline;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

  private final OrderLineRepository orderLineRepository;
  private final OrderLineMapper orderLineMapper;

  public Integer saveOrderLine(OrderLineRequest request) {
    var order = orderLineMapper.toOrderLine(request);
    return orderLineRepository.save(order).getId();
  }

  public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
    return orderLineRepository.findAllByOrderId(orderId)
        .stream()
        .map(orderLineMapper::toOrderLineResponse)
        .collect(Collectors.toList());
  }
}
