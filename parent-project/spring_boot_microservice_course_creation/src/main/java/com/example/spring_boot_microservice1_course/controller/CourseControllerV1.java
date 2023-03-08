package com.example.spring_boot_microservice1_course.controller;


import com.example.spring_boot_microservice1_course.model.Course;
import com.example.spring_boot_microservice1_course.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("/api/v1/creation")
public class CourseControllerV1 {

    public CourseControllerV1(CourseService courseService) {
        this.courseService = courseService;
    }

    private CourseService courseService;

    @PostMapping("/createcourse")
    public ResponseEntity saveCourse(@RequestBody Course course)
    {
       return  new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }


    @DeleteMapping("/deletecourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id)
    {

        courseService.deleteCourse(id);
         return  new ResponseEntity<>( HttpStatus.OK);
    }


    @GetMapping("/getcourses")
    public ResponseEntity<?> getAllCourses( )
    {

       return  ResponseEntity.ok(courseService.findAllCourse());

    }


}



