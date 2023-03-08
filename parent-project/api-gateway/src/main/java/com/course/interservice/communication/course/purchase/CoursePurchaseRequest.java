package com.course.interservice.communication.course.purchase;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "purchase-service" ,path="api/v1/purchase"
        //url = "http://localhost:4444/api/v1/purchase"
        ,configuration = FeignConfigurationCoursePurchase.class)

public interface CoursePurchaseRequest {

    @PostMapping("/savepurchase")
     Object saveCourse(@RequestBody Object purchase);

    @GetMapping("/getpurchasedcourses/{userId}")
     List<Object>  getAllPurchasedCourses(@PathVariable Long  userId);

}
