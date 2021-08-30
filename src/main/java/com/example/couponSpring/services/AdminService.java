package com.example.couponSpring.services;

import com.example.couponSpring.beans.Company;
import com.example.couponSpring.beans.Customer;
import com.example.couponSpring.exceptions.CouponSystemException;

import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(Company company) throws CouponSystemException;
    void deleteCompany(Company company) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getSingleCompany(int companyID) throws CouponSystemException;

    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerID) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getSingleCustomer(int customerID) throws CouponSystemException;


}
