package com.coursepurchase.Controller;


import com.coursepurchase.model.Purchase;
import com.coursepurchase.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("/api/v1/purchase")
public class CourseControllerV1 {

    public CourseControllerV1(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    private PurchaseService purchaseService;

    @PostMapping("/savepurchase")
    public ResponseEntity saveCourse(@RequestBody Purchase purchase)
    {
       return  new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.CREATED);
    }





    @GetMapping("/getpurchasedcourses/{userId}")
    public ResponseEntity<?> getAllPurchasedCourses(@PathVariable Long  userId)
    {

       return  ResponseEntity.ok(purchaseService.findAllPurchasesofUser(userId));

    }


}



