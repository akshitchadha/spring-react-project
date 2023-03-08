package com.course.interservice.communication.course.purchase;

import com.course.api.gateway.security.CustomUserDetails;
import com.course.interservice.communication.course.purchase.CoursePurchaseRequest;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/purchase")
public class PurchaseController {

    @Autowired
    CoursePurchaseRequest coursePurchaseRequest;
    @PostMapping("/savepurchase")
    public ResponseEntity saveCourse(@RequestBody Object purchase)
    {
        return  new ResponseEntity<>(coursePurchaseRequest.saveCourse(purchase), HttpStatus.CREATED);
    }





    @GetMapping("/getpurchasedcourses")
    public ResponseEntity<?> getAllPurchasedCourses(@AuthenticationPrincipal CustomUserDetails
                                                                customUser)
    {

        return  ResponseEntity.ok(coursePurchaseRequest.getAllPurchasedCourses(customUser.getId()));

    }

}
