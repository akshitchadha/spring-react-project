package com.course.interservice.communication.course.creation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/course")
public class CourseController {

    @Autowired
   private CourseServiceRequest courseServiceRequest;

    @PostMapping("/createcourse")
    public ResponseEntity saveCourse(@RequestBody Object course)
    {
        return  new ResponseEntity<>(courseServiceRequest.saveCourse(course), HttpStatus.CREATED);
    }


    @DeleteMapping("/deletecourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id)
    {
        courseServiceRequest.deleteCourse(id);
        return  new ResponseEntity<>( HttpStatus.OK);
    }


    @GetMapping("/getcourses")
    public ResponseEntity<?> getAllCourses( )
    {

        return  ResponseEntity.ok(courseServiceRequest.getAllCourses());

    }


}
