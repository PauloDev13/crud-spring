package com.prmorais.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prmorais.crudspring.enums.Category;
import com.prmorais.crudspring.enums.Status;
import com.prmorais.crudspring.enums.converters.CategoryConverter;
import com.prmorais.crudspring.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//Exclui o registro logicamente sem excluí-lo da tabela mudando a coluna status
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
//Filtra os registros com status ativo
@Where(clause = "status = 'Ativo'")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("_id")
  private Long id;

  @NotBlank
  @NotNull
  @Length(min = 5, max = 100)
  @Column(nullable = false, length = 100)
  private String name;

  @NotNull
  @Column(nullable = false, length = 50)
  @Convert(converter = CategoryConverter.class)
  private Category category;

  @NotNull
  @Column(nullable = false, length = 10)
  @Convert(converter = StatusConverter.class)
  private Status status = Status.ACTIVE;

  @NotNull
  @NotEmpty
  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
  private List<Lesson> lessons = new ArrayList<>();
}
