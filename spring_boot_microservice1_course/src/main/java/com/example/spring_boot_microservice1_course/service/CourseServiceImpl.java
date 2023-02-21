package com.example.spring_boot_microservice1_course.service;

import com.example.spring_boot_microservice1_course.model.Course;
import com.example.spring_boot_microservice1_course.repository.CourseRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {


    private final CourseRespository courseRespository;

    @Override
    public Course saveCourse(Course course) {

        course.setCreateTime(LocalDateTime.now());
        return courseRespository.save(course);

    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRespository.deleteById(courseId);

    }
    @Override
    public List<Course> findAllCourse() {

        return courseRespository.findAll();
    }

}
