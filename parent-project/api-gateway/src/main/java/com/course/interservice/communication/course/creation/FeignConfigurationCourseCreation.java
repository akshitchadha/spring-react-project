package com.course.interservice.communication.course.creation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

@Configuration
public class FeignConfigurationCourseCreation {

    @Bean
    public BasicAuthenticationInterceptor basicAuthenticationCourseCreationMicroservice
            (@Value("${app.course.creation.username}") String creationServiceUserName,
             @Value("${app.course.creation.password}") String creationServicePassWord
            ) {

        return new BasicAuthenticationInterceptor(creationServiceUserName, creationServicePassWord);
    }



//    @Bean("BasicAuthenticationPurchaseMicroservice")
//    public BasicAuthenticationInterceptor basicAuthenticationCoursePurchaseMicroservice
//            (@Value("${app.course.purchase.username}") String purchaseServiceUserName,
//             @Value("${app.course.purchase.password}") String purchaseServicePassWord
//            ) {
//
//        return new BasicAuthenticationInterceptor(purchaseServiceUserName, purchaseServicePassWord);
//    }

}
