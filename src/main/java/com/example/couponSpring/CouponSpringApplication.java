package com.example.couponSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CouponSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(CouponSpringApplication.class, args);
/*
		try {
			ApplicationContext ctx =  SpringApplication.run(CouponSpringApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}


}
