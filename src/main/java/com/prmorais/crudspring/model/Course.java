package com.prmorais.crudspring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(nullable = false, length = 50)
  private String category;
}
