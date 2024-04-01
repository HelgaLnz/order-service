package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final UserService userService;

  public Order create(OrderDto orderDto) {
    return orderRepository.save(Order.builder()
      .description(orderDto.description())
      .status(orderDto.statusOrder())
      .user(userService.getEntity(orderDto.userId()))
      .build());
  }

  public Order getById(Integer id) {
    return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id'" + id + "'"));
  }

  public Page<Order> getOrders(Pageable pageable) {
    return orderRepository.findAll(pageable);
  }

  public Order change(Integer id, OrderDto orderDto) {
    return orderRepository.save(orderRepository.findById(id)
      .map(orderNew -> {
        orderNew.setDescription(orderDto.description());
        orderNew.setStatus(orderDto.statusOrder());
        orderNew.setUser(userService.getEntity(orderDto.userId()));
        return orderRepository.save(orderNew);
      }).orElse(Order.builder()
        .description(orderDto.description())
        .status(orderDto.statusOrder())
        .user(userService.getEntity(orderDto.userId()))
        .build()
      ));
  }

  public Order delete(Integer id) {
    Order orderDeleted = orderRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Order not found with id'" + id + "'"));
    orderRepository.deleteById(id);
    return orderDeleted;
  }
}
