package com.prmorais.crudspring.service;

import com.prmorais.crudspring.model.Course;
import com.prmorais.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CourseService {
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> list() {
    return courseRepository.findAll();
  }

  public Optional<Course> findById(@PathVariable @NotNull @Positive Long id) {
    return courseRepository.findById(id);
  }

  public Course create(@Valid Course course) {
    return courseRepository.save(course);
  }

  public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course) {
    return courseRepository.findById(id)
        .map(data -> {
          data.setName(course.getName());
          data.setCategory(course.getCategory());
          return courseRepository.save(data);
        });
  }

  public boolean delete(@NotNull @Positive Long id) {
    return courseRepository.findById(id)
        .map(data -> {
          courseRepository.deleteById(id);
          return true;
        })
        .orElse(false);
  }
}