package com.prmorais.crudspring.controller;

import com.prmorais.crudspring.model.Course;
import com.prmorais.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
  private final CourseRepository courseRepository;

  @GetMapping
  public List<Course> list() {
    return courseRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) {
    return courseRepository.findById(id)
            .map(data -> ResponseEntity.ok().body(data))
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody @Valid Course course) {
    return courseRepository.save(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course ) {
    return courseRepository.findById(id)
            .map(data -> {
              data.setName(course.getName());
              data.setCategory(course.getCategory());
              Course courseUpdated = courseRepository.save(data);
              return ResponseEntity.ok().body(courseUpdated);
            })
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
    return courseRepository.findById(id)
            .map(data -> {
              courseRepository.deleteById(id);
              return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
  }
}
