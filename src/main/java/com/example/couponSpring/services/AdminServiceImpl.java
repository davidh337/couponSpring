package com.example.couponSpring.services;

import com.example.couponSpring.beans.Company;
import com.example.couponSpring.beans.Customer;
import com.example.couponSpring.exceptions.CouponSystemException;
import com.example.couponSpring.exceptions.ErrMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService{
    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if(!(email.equals("admin@admin.com") && password.equals("admin")))
        {
            throw new CouponSystemException(ErrMsg.LOGIN_ERROR);
        }
        return true;
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {

        if(companyRepository.existsFindByName(company.getName()))
        {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXIST);
        }
        if(companyRepository.existsFindByEmail(company.getEmail()))
        {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_EXIST);
        }
        this.companyRepository.save(company);

    }

    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsFindByName(company.getName()))
        {
            Company findCompany = companyRepository.findByName(company.getName());
            if (company.getId() != findCompany.getId())
            {
                throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXIST);
            }
        }

        companyRepository.saveAndFlush(company);

    }

    @Override
    public void deleteCompany(Company company) throws CouponSystemException {
        if(!companyRepository.existsById(company.getId())){
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        companyRepository.deleteById(company.getId());
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyID) throws CouponSystemException {
        Company company =    companyRepository.findById(companyID).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_FOUND) );
        //company.setCoupons(couponRepository.findAllByCompanyId(companyID));
        return company;
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if(customerRepository.existsByEmail(customer.getEmail()))
        {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_EXIST);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws CouponSystemException {
        if (customerRepository.existsFindByEmail(customer.getEmail()))
        {
            Customer findCustomer = customerRepository.findByEmail(customer.getEmail());
            if (customer.getId() != findCustomer.getId())
            {
                throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_EXIST);
            }
        }

        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
        if(!customerRepository.existsById(customerID)){
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        customerRepository.deleteById(customerID);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerID) throws CouponSystemException {
        return customerRepository.findById(customerID).orElseThrow(()->new CouponSystemException(ErrMsg.ID_NOT_FOUND) );
    }
}
