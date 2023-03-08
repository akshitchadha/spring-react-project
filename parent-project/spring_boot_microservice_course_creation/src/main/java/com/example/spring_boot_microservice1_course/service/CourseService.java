package com.example.spring_boot_microservice1_course.service;

import com.example.spring_boot_microservice1_course.model.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);

    void deleteCourse(Long courseId);

    List<Course> findAllCourse();
}
