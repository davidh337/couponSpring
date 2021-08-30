package com.example.couponSpring.services;

import com.example.couponSpring.beans.Category;
import com.example.couponSpring.beans.Coupon;
import com.example.couponSpring.beans.Customer;
import com.example.couponSpring.exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    public void purchaseCoupon(Coupon coupon) throws SQLException, CouponSystemException;
    public ArrayList<Coupon> getCustomerCoupons() throws SQLException;
    public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException;
    public ArrayList<Coupon> getCustomerCoupons(double price) throws SQLException ;
    public Customer getCustomerDetails() throws SQLException ;
    public List<Coupon> getAllCoupons();




    }
