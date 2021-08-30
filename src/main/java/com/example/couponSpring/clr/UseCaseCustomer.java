package com.example.couponSpring.clr;

import com.example.couponSpring.beans.Category;
import com.example.couponSpring.beans.Coupon;
import com.example.couponSpring.beans.Customer;
import com.example.couponSpring.login.ClientType;
import com.example.couponSpring.login.LoginManager;
import com.example.couponSpring.services.ClientService;
import com.example.couponSpring.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(5)
@Component
@RequiredArgsConstructor
public class UseCaseCustomer implements CommandLineRunner {
    private final ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CUSTOMER CASE:");

        LoginManager loginManager = ctx.getBean(LoginManager.class);
        ClientService clientService = null;

        try {
            clientService = loginManager.login("yudith@mail.com", "yuyu", ClientType.Customer);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        if (clientService instanceof CustomerService)
        {
            Customer customer =((CustomerService) clientService).getCustomerDetails();
            List<Coupon> allCoupons =  ((CustomerService) clientService).getAllCoupons();
            System.out.println("allCoupons!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            /*System.out.println(allCoupons);
            System.out.println(allCoupons.get(0));*/
            ((CustomerService) clientService).purchaseCoupon(allCoupons.get(0));
            try {
                //((CustomerService) clientService).purchaseCoupon(allCoupons.get(0));
               // ((CustomerService) clientService).purchaseCoupon(allCoupons.get(0));
                ((CustomerService) clientService).purchaseCoupon(allCoupons.get(1));

            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            System.out.println(customer);
            System.out.println("Customers coupons");
            System.out.println(((CustomerService) clientService).getCustomerCoupons());;

            System.out.println("Customers coupons by maxprice 1");
            System.out.println(((CustomerService) clientService).getCustomerCoupons(100));;


            System.out.println("Customers coupons by maxprice 2");
            System.out.println(((CustomerService) clientService).getCustomerCoupons(20));;



            System.out.println("Customers coupons by category 1");
            System.out.println(((CustomerService) clientService).getCustomerCoupons(Category.FOOD));;

            System.out.println("Customers coupons by category 2");
            System.out.println(((CustomerService) clientService).getCustomerCoupons(Category.VACATION));;

        }
    }
}
