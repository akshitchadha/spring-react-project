package com.course.interservice.communication.course.creation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "course-service",path = "api/v1/creation",
        //url = "http://localhost:3333/api/v1/creation" ,

        configuration = FeignConfigurationCourseCreation.class)
 interface  CourseServiceRequest {

    @PostMapping("/createcourse")
    Object saveCourse(@RequestBody Object course);


    @DeleteMapping("/deletecourse/{id}")
    void deleteCourse(@PathVariable("id") Long id);


    @GetMapping("/getcourses")
    List<Object> getAllCourses();


}
