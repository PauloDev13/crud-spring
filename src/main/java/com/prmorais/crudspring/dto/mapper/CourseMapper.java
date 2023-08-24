package com.prmorais.crudspring.dto.mapper;

import com.prmorais.crudspring.dto.CourseDTO;
import com.prmorais.crudspring.enums.Category;
import com.prmorais.crudspring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
  public CourseDTO toDTO(Course course) {
    if(course == null) {
      return null;
    }
    return new CourseDTO(course.getId(), course.getName(), "Front-end");
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
    course.setCategory(Category.FRONTEND);
    course.setStatus("Ativo");
    return course;
  }
}
