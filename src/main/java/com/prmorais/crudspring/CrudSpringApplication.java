package com.prmorais.crudspring;

import com.prmorais.crudspring.enums.Category;
import com.prmorais.crudspring.model.Course;
import com.prmorais.crudspring.model.Lesson;
import com.prmorais.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudSpringApplication.class, args);
  }

  @Bean
//  @Profile("test")
  CommandLineRunner initDatabase(CourseRepository courseRepository) {
    return args -> {
      courseRepository.deleteAll();

      for (int i = 1; i < 50; i++) {
        Course c = new Course();
        c.setName("Angular com Spring " + i);
        c.setCategory(Category.FRONTEND);

        Lesson l = new Lesson();
        l.setName("Introdução");
        l.setYoutubeUrl("jODKlKKpe9j");
        l.setCourse(c);
        c.getLessons().add(l);

        Lesson l1 = new Lesson();
        l1.setName("Contexto");
        l1.setYoutubeUrl("jODKlKK23rt");
        l1.setCourse(c);
        c.getLessons().add(l1);

        courseRepository.save(c);

      }
    };
  }
}
