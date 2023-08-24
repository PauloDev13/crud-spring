package com.prmorais.crudspring.service;

import com.prmorais.crudspring.dto.CourseDTO;
import com.prmorais.crudspring.dto.mapper.CourseMapper;
import com.prmorais.crudspring.exception.RecordNotFoundException;
import com.prmorais.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

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

  public List<CourseDTO> list() {
    return courseRepository.findAll()
        .stream()
        .map(courseMapper::toDTO)
        .toList();
  }

  public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
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
          courseFound.setName(courseDTO.name());
          courseFound.setCategory(courseDTO.category());
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