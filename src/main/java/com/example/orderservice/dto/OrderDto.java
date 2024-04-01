package com.example.orderservice.dto;

import com.example.orderservice.entity.Order;

public record OrderDto(String description, Order.StatusOrder statusOrder, Integer userId) {
}
