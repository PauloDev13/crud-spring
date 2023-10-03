package com.prmorais.crudspring.controller;

import com.prmorais.crudspring.dto.CourseDTO;
import com.prmorais.crudspring.dto.CoursePageDTO;
import com.prmorais.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {
  private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public CoursePageDTO list(
        @RequestParam @PositiveOrZero int page,
        @RequestParam @Positive @Max(100) int size)
    {
      return courseService.list(page, size);
    }
    /*  @GetMapping
        public List<CourseDTO> list() {
        return courseService.list();
    }*/

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @NotNull @Valid CourseDTO courseDTO) {
        return courseService.create(courseDTO);
      }

    @PutMapping("/{id}")
      public CourseDTO update(@PathVariable @NotNull @Positive Long id,
                           @RequestBody @Valid @NotNull CourseDTO courseDTO ) {
        return courseService.update(id, courseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
      courseService.delete(id);
    }
}
