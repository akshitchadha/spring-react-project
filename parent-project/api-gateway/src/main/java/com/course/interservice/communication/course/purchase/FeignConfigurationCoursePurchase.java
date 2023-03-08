package com.course.interservice.communication.course.purchase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
@Configuration
public class FeignConfigurationCoursePurchase {


    @Bean
    public BasicAuthenticationInterceptor basicAuthenticationCoursePurchaseMicroservice
            (@Value("${app.course.purchase.username}") String purchaseServiceUserName,
             @Value("${app.course.purchase.password}") String purchaseServicePassWord
            ) {

        return new BasicAuthenticationInterceptor(purchaseServiceUserName, purchaseServicePassWord);
    }

}
