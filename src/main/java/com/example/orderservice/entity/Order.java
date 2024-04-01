package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

  public enum StatusOrder {
    CREATED,
    PROCESSED,
    REJECTED,
    RECEIVED
  }
}
