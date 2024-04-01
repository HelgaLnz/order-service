package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "status_order", nullable = false)
  @Enumerated
  private StatusOrder status;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonManagedReference
  private User user;

  public enum StatusOrder{
    CREATED,
    PROCESSED,
    REJECTED,
    RECEIVED
  }
}
