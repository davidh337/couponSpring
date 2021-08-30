package com.example.couponSpring.clr;

import com.example.couponSpring.repos.CompanyRepository;
import com.example.couponSpring.repos.CouponRepository;
import com.example.couponSpring.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private  final CompanyRepository companyRepository;
    private  final CustomerRepository customerRepository;
    private  final CouponRepository couponRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("DAVIDDDDDDDDDD!!!!!!!");



    }
}
