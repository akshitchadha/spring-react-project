package com.example.spring_boot_microservice1_course.repository;

import com.example.spring_boot_microservice1_course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRespository extends JpaRepository<Course, Long> {
}
