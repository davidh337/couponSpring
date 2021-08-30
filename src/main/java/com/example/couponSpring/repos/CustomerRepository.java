package com.example.couponSpring.repos;

import com.example.couponSpring.beans.Coupon;
import com.example.couponSpring.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);


    boolean existsFindByEmail(String email);

    @Override
    boolean existsById(Integer integer);


    Customer findByEmail(String email);

    boolean existsByCoupons(Coupon coupon);



}
