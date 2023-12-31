package com.prmorais.crudspring.service;

import com.prmorais.crudspring.dto.CourseDTO;
import com.prmorais.crudspring.dto.CoursePageDTO;
import com.prmorais.crudspring.dto.mapper.CourseMapper;
import com.prmorais.crudspring.exception.RecordNotFoundException;
import com.prmorais.crudspring.model.Course;
import com.prmorais.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CourseService {
  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
  }

    public CoursePageDTO list(
        @PositiveOrZero int page,
        @Positive @Max(100) int size)
    {
    Page<Course> pageCourse  = courseRepository.findAll(PageRequest.of(page, size));
    List<CourseDTO> courses = pageCourse.get().map(courseMapper::toDTO).toList();
    return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
  }

/*  public List<CourseDTO> list() {
    return courseRepository.findAll()
        .stream()
        .map(courseMapper::toDTO)
        .toList();
  }*/

  public CourseDTO findById(@NotNull @Positive Long id) {
    return courseRepository.findById(id)
        .map(courseMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public CourseDTO create(@Valid @NotNull CourseDTO courseDTO) {
    return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTO)));
  }

  public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
    return courseRepository.findById(id)
        .map(courseFound -> {
          Course course = courseMapper.toEntity(courseDTO);

          courseFound.setName(courseDTO.name());
          courseFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
          courseFound.getLessons().clear();

          course.getLessons().forEach(courseFound.getLessons()::add);
          return courseMapper.toDTO(courseRepository.save(courseFound));
        })
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@NotNull @Positive Long id) {
    courseRepository.delete(
        courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id))
    );
  }
}