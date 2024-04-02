package com.example.orderservice.service;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.User;
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
class UserServiceTest {

  @Autowired
  private UserService userService;

  private UserDto dto;

  private final Random random = new Random();

  @BeforeEach
  public void setUp() {
    dto = new UserDto("username" + random.nextInt(), "email" + random.nextInt());
  }

  @Test
  void whenCreateUser_thenReturnUser() {
    User user = userService.create(dto);
    Assertions.assertEquals(dto.username(), user.getUsername());
    Assertions.assertEquals(dto.email(), user.getEmail());
  }

  @Test
  void whenChangeUser_thenReturnUserChanged() {
    User userCreated = userService.create(dto);
    UserDto changedUser = new UserDto(dto.username() + random.nextInt(), dto.email() + random.nextInt());
    User user = userService.change(userCreated.getId(), changedUser);
    Assertions.assertEquals(changedUser.username(), user.getUsername());
    Assertions.assertEquals(changedUser.email(), user.getEmail());
  }
}