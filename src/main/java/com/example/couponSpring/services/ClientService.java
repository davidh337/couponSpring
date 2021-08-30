package com.example.couponSpring.services;

import com.example.couponSpring.exceptions.CouponSystemException;
import com.example.couponSpring.repos.CompanyRepository;
import com.example.couponSpring.repos.CouponRepository;
import com.example.couponSpring.repos.CustomerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class ClientService {

    @Autowired
    protected  CouponRepository  couponRepository;
    @Autowired
    protected  CustomerRepository customerRepository;
    @Autowired
    protected  CompanyRepository companyRepository;


    public abstract boolean login(String email, String password) throws CouponSystemException;


}
