package com.example.spring_boot_microservice1_course.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data // getter setter equals  hashcode and to string
@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "title",length = 100,nullable = false)
    private  String title;

    @Column(name = "subtitle",length = 100,nullable = false)
    private  String subtitle;

    @Column(name = "price",nullable = false)
    private  Double price;


    @Column(name = "createTime",nullable = false)
    private LocalDateTime createTime;
}
