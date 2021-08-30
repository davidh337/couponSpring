package com.example.couponSpring.repos;

import com.example.couponSpring.beans.Category;
import com.example.couponSpring.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    //@Query(value = "Select * from coupons where company_id = ?")
    //List<Coupon> getCouponsByCompany(int companyID);
    //@Query(value = "Select * from coupons where company_id = ? and category_id = ?")
    //List<Coupon> getCouponsByCompany(int companyID, Category category);
    //@Query(value getCouponsByCustomer= "Select * from coupons where company_id = ? and AND price` <=?;")
    //List<Coupon> getCouponsByCompany(int companyID, double price);

   // @Query(value = "Select * from coupons cp inner join  where customer_id = ?")
   // List<Coupon> getCouponsByCustomer(int customerID);
   // @Query(value = "Select * from coupons where customer_id = ? and category_id = ?")
    //List<Coupon> getCouponsByCustomer(int customerID, Category category);
    //@Query(value = "Select * from coupons where customer_id = ? and AND price` <=?;")
    //List<Coupon> getCouponsByCustomer(int customerID, double price);


    ArrayList<Coupon> findAllByCompanyId(int companyId);
    ArrayList<Coupon> findAllByCompanyIdAndPriceLessThanEqual(int companyId,double price);
    ArrayList<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);
    boolean existsFindByDescription(String description);

    Coupon findByDescription(String description);

    void deleteByEndDateBefore(Date expiryDate);

    @Query(value =
            "SELECT c.* FROM `spring-coupon`.coupons c " +
                    "INNER JOIN `spring-coupon`.customers_coupons cc " +
                    "ON cc.coupons_id = c.id " +
                    "WHERE cc.customer_id =:id", nativeQuery = true)
    ArrayList<Coupon> findCouponsById(@Param("id") int Id);

    @Query(value =
            "SELECT c.* FROM `spring-coupon`.coupons c " +
                    "INNER JOIN `spring-coupon`.customers_coupons cc " +
                    "ON cc.coupons_id = c.id " +
                    "WHERE cc.customer_id =:id " +
                    "AND   c.category =:category", nativeQuery = true)
    ArrayList<Coupon> findCouponsByIdAndCategory(@Param("id") int Id, @Param("category") String category);

    @Query(value =
            "SELECT c.* FROM `spring-coupon`.coupons c " +
                    "INNER JOIN `spring-coupon`.customers_coupons cc " +
                    "ON cc.coupons_id = c.id " +
                    "WHERE cc.customer_id =:id " +
                    "AND   c.price <=:price", nativeQuery = true)
    ArrayList<Coupon> findCouponsByIdAndMaxPrice(@Param("id") int Id, @Param("price") double price);

}
