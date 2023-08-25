package com.prmorais.crudspring.dto.mapper;

import com.prmorais.crudspring.dto.LessonDTO;
import com.prmorais.crudspring.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

  public LessonDTO toDTO(Lesson lesson) {
    if (lesson == null) {
      return null;
    }

    return new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl());
  }

  public Lesson toEntity(LessonDTO lessonDTO) {
    if (lessonDTO == null) {
      return null;
    }
    Lesson lesson = new Lesson();
    lesson.setId(lessonDTO.id());
    lesson.setName(lessonDTO.name());
    lesson.setYoutubeUrl(lessonDTO.youtubeUrl());

    return lesson;
  }
}
