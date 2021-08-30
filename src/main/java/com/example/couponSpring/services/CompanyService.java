package com.example.couponSpring.services;

import com.example.couponSpring.beans.Category;
import com.example.couponSpring.beans.Company;
import com.example.couponSpring.beans.Coupon;
import com.example.couponSpring.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon) throws CouponSystemException;
    void updateCoupon(Coupon coupon) throws CouponSystemException;
    void deleteCoupon(Coupon coupon) throws CouponSystemException;
    List<Coupon> getCompanyCoupons();
    List<Coupon> getCompanyCoupons(Category category);
    List<Coupon> getCompanyCoupons(double maxPrice);
    Company getCompanyDetails();


}
