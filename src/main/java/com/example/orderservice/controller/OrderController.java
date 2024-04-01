package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/orders/{id}")
  public ResponseEntity<Order> getById(@PathVariable Integer id) {
    return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
  }

  @GetMapping("/orders")
  public ResponseEntity<Page<Order>> getById(
    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
    @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
    return new ResponseEntity<>(orderService.getOrders(PageRequest.of(offset, limit)), HttpStatus.OK);
  }

  @PostMapping("/orders")
  public ResponseEntity<Order> create(@RequestBody OrderDto orderDto) {
    return new ResponseEntity<>(orderService.create(orderDto), HttpStatus.CREATED);
  }

  @PutMapping("/orders/{id}")
  public ResponseEntity<Order> change(@PathVariable Integer id, @RequestBody OrderDto orderDto) {
    return new ResponseEntity<>(orderService.change(id, orderDto), HttpStatus.CREATED);
  }

  @DeleteMapping("/orders/{id}")
  public ResponseEntity<Order> change(@PathVariable Integer id) {
    return new ResponseEntity<>(orderService.delete(id), HttpStatus.CREATED);
  }
}
