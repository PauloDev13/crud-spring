package com.prmorais.crudspring.dto.mapper;

import com.prmorais.crudspring.dto.CourseDTO;
import com.prmorais.crudspring.dto.LessonDTO;
import com.prmorais.crudspring.enums.Category;
import com.prmorais.crudspring.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {
  public CourseDTO toDTO(Course course) {
    if(course == null) {
      return null;
    }

    List<LessonDTO> lessons = course.getLessons()
        .stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
        .toList();
    return new CourseDTO(
        course.getId(),
        course.getName(),
        course.getCategory().getValue(),
        lessons);
  }

  public Course toEntity(CourseDTO courseDTO) {
    if(courseDTO == null) {
      return null;
    }

    Course course = new Course();
    if (courseDTO.id() != null) {
      course.setId(courseDTO.id());
    }
    course.setName(courseDTO.name());
    course.setCategory(convertCategoryValue(courseDTO.category()));
    return course;
  }
  public Category convertCategoryValue(String value) {
    if (value == null) {
      return null;
    }
    return switch (value) {
      case "Front-end" -> Category.FRONTEND;
      case "Back-end" -> Category.BACKEND;
        default -> throw new IllegalArgumentException("Categoria inválida: " + value);
    };
  }
}
