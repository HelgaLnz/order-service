package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.User;
import com.example.orderservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @Autowired
  private UserRepository userRepository;

  private OrderDto dto;

  @BeforeEach
  public void setUp() {
    Random random = new Random();
    User user = userRepository.save(User.builder()
      .email("email" + random.nextInt())
      .username("username" + random.nextInt())
      .build());

    dto = new OrderDto("desc" + random.nextInt(), Order.StatusOrder.CREATED, user.getId());
  }

  @Test
  void whenCreateOrder_thenReturnOrder() {
    Order order = orderService.create(dto);
    Assertions.assertEquals(dto.description(), order.getDescription());
  }

  @Test
  void whenGetByIdOrder_thenReturnOrder() {
    Order orderCreated = orderService.create(dto);
    Order order = orderService.getById(orderCreated.getId());
    Assertions.assertEquals(orderCreated.getDescription(), order.getDescription());
  }
}