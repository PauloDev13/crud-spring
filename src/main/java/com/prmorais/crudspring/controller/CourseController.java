package com.prmorais.crudspring.controller;

import com.prmorais.crudspring.model.Course;
import com.prmorais.crudspring.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return courseRepository.findById(id)
            .map(data -> ResponseEntity.ok().body(data))
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody Course course) {
    return courseRepository.save(course);
  }
//  public ResponseEntity<Course> create(@RequestBody Course course){
//    return ResponseEntity.status(HttpStatus.CREATED)
//            .body(courseRepository.save(course));
//  }
}
