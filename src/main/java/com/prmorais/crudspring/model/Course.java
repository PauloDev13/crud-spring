package com.prmorais.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

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
  @Length(max = 50)
  @Pattern(regexp = "Back-end|Front-end")
  @Column(nullable = false, length = 50)
  private String category;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Ativo|Inativo")
  @Column(nullable = false, length = 10)
  private String status = "Ativo";
}
