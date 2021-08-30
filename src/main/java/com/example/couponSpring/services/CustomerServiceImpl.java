package com.example.couponSpring.services;

import com.example.couponSpring.beans.Category;
import com.example.couponSpring.beans.Coupon;
import com.example.couponSpring.beans.Customer;
import com.example.couponSpring.exceptions.CouponSystemException;
import com.example.couponSpring.exceptions.ErrMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceImpl extends ClientService  implements CustomerService{
    private Customer customer;
    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if(!customerRepository.existsByEmail(email)) {
            throw new CouponSystemException(ErrMsg.LOGIN_ERROR);
        }
        Customer c1=customerRepository.findByEmail(email);
        if (!c1.getPassword().equals(password)) {
            throw new CouponSystemException(ErrMsg.LOGIN_ERROR);
        }
        customer=c1;
        return true;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws SQLException, CouponSystemException {
        if (!couponRepository.existsById(coupon.getId()))
        {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
        if (customerRepository.existsByCoupons(coupon))
        {
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_BUY);

        }
        if (coupon.getAmount()== 0)
        {
            throw new CouponSystemException(ErrMsg.NO_COUPON_AVAIABLE);
        }

        coupon.setAmount(coupon.getAmount()-1);
        couponRepository.saveAndFlush(coupon);

        List<Coupon> coupons = customer.getCoupons();
        coupons.add(coupon);
        customer.setCoupons(coupons);

        customerRepository.saveAndFlush(customer);


    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons() throws SQLException {
        return couponRepository.findCouponsById(customer.getId());
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException {
        return couponRepository.findCouponsByIdAndCategory(customer.getId(),category.name());
    }

    @Override
    public ArrayList<Coupon> getCustomerCoupons(double price) throws SQLException {
        return couponRepository.findCouponsByIdAndMaxPrice(customer.getId(),price);
    }

    @Override
    public Customer getCustomerDetails() throws SQLException {
        return customerRepository.getById(customer.getId());
    }


    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

}
