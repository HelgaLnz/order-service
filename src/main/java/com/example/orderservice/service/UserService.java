package com.example.orderservice.service;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.User;
import com.example.orderservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  protected User getEntity(Integer id) {
    return userRepository.findById(id).orElseThrow();
  }

  public User create(UserDto userDto) {
    if (!userRepository.existsByUsername(userDto.username()))

      return userRepository.save(User.builder()
        .email(userDto.email())
        .username(userDto.username())
        .orderList(Collections.emptyList())
        .build());


    else throw new RuntimeException("User not found with username'" + userDto.username() + "'");
  }

  public User getById(Integer id) {
    return userRepository.findById(id).orElseThrow();
  }

  public Page<User> getAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  public User change(Integer id, UserDto userDto) {
    if (!userRepository.existsByUsername(userDto.username())) {

      return userRepository.findById(id)
        .map(category -> {
          category.setUsername(userDto.username());
          return userRepository.save(category);
        }).orElse(User.builder()
          .username(userDto.username())
          .build()
        );

    } else
      throw new RuntimeException("User already exists with username '" + userDto.username() + "'");
  }

  public User delete(Integer id) {
    User userDeleted = userRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("User with " + id + " not found."));
    userRepository.deleteById(id);

    return userDeleted;
  }
}
