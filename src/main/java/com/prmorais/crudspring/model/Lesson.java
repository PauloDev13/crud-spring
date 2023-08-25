package com.prmorais.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

//  @NotBlank
//  @NotNull
  @Column(nullable = false, length = 100)
  private String name;

//  @NotBlank
//  @NotNull
  @Column(nullable = false, length = 11)
  private String youtubeUrl;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Course course;
}
