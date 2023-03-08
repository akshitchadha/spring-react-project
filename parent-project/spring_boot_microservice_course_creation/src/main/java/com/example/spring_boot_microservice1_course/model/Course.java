package com.example.spring_boot_microservice1_course.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

 // getter setter equals  hashcode and to string
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

     public Long getId() {
         return id;
     }

     public String getTitle() {
         return title;
     }

     public String getSubtitle() {
         return subtitle;
     }

     public Double getPrice() {
         return price;
     }

     public LocalDateTime getCreateTime() {
         return createTime;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public void setTitle(String title) {
         this.title = title;
     }

     public void setSubtitle(String subtitle) {
         this.subtitle = subtitle;
     }

     public void setPrice(Double price) {
         this.price = price;
     }

     public void setCreateTime(LocalDateTime createTime) {
         this.createTime = createTime;
     }
 }
