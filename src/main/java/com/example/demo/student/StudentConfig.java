package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository)   {
        return ags -> {
            Student marty = new Student(
                    "Marty",
                    "marty@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student kim = new Student(
                    "Kim",
                    "kim@gmail.com",
                    LocalDate.of(1999, Month.SEPTEMBER, 20)
            );
            repository.saveAll(
                    List.of(marty, kim)
            );
        };

    }
}
