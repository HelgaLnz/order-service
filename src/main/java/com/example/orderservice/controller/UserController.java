package com.example.orderservice.controller;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.User;
import com.example.orderservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

  private final UserService userService;

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getById(@PathVariable Integer id) {
    return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
  }

  @GetMapping("/users")
  public ResponseEntity<Page<User>> getById(
    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
    @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
    return new ResponseEntity<>(userService.getAllUsers(PageRequest.of(offset, limit)), HttpStatus.OK);
  }

  @PostMapping("/users")
  public ResponseEntity<User> create(@RequestBody UserDto userDto) {
    return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> change(@PathVariable Integer id, @RequestBody UserDto userDto) {
    return new ResponseEntity<>(userService.change(id, userDto), HttpStatus.CREATED);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<User> change(@PathVariable Integer id) {
    return new ResponseEntity<>(userService.delete(id), HttpStatus.CREATED);
  }
}
