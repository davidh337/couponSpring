package com.example.couponSpring.services;

import com.example.couponSpring.beans.Category;
import com.example.couponSpring.beans.Company;
import com.example.couponSpring.beans.Coupon;
import com.example.couponSpring.exceptions.CouponSystemException;
import com.example.couponSpring.exceptions.ErrMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService{
    private Company company;
    @Override
    public boolean login(String email, String password) throws CouponSystemException {
         if(!companyRepository.existsFindByEmail(email)) {
             throw new CouponSystemException(ErrMsg.LOGIN_ERROR);
         }
         Company c1 = companyRepository.findByEmail(email);
         if (!c1.getPassword().equals(password)) {
             throw new CouponSystemException(ErrMsg.LOGIN_ERROR);
         }
        company=c1;
        return true;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {

        if(couponRepository.existsFindByDescription(coupon.getDescription()))
        {throw new CouponSystemException(ErrMsg.COUPON_EXIST);}

        coupon.setCompany(company);
        company.builder().coupon(coupon).build();
        this.couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(coupon.getId()))
            {throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);}
        if (couponRepository.existsFindByDescription(coupon.getDescription()))
        {
            Coupon c1 = couponRepository.findByDescription(coupon.getDescription());
            if (c1.getId()!= coupon.getId())
            {throw new CouponSystemException(ErrMsg.COUPON_EXIST);}
        }
        this.couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(Coupon coupon) throws CouponSystemException {
        if(!couponRepository.existsById(coupon.getId())) {
            throw new CouponSystemException(ErrMsg.ID_NOT_FOUND);
        }
            this.couponRepository.deleteById(coupon.getId());
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.findAllByCompanyId(company.getId());
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepository.findAllByCompanyIdAndCategory(company.getId(),category);

      //  return couponRepository.getCouponsByCompany(company.getId(),category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return couponRepository.findAllByCompanyIdAndPriceLessThanEqual(company.getId(),maxPrice);

       // return couponRepository.getCouponsByCompany(company.getId(),maxPrice);
    }

    @Override
    public Company getCompanyDetails() {
        return companyRepository.getById(company.getId());
    }
}
