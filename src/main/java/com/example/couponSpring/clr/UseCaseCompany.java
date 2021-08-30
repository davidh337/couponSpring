package com.example.couponSpring.clr;

import com.example.couponSpring.beans.Category;
import com.example.couponSpring.beans.Company;
import com.example.couponSpring.beans.Coupon;
import com.example.couponSpring.login.ClientType;
import com.example.couponSpring.login.LoginManager;
import com.example.couponSpring.repos.CompanyRepository;
import com.example.couponSpring.services.ClientService;
import com.example.couponSpring.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Order(3)
@Component
@RequiredArgsConstructor
public class UseCaseCompany implements CommandLineRunner {
    private final ApplicationContext ctx;

    private  final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Company CASE:");

        Coupon coupon1 = Coupon.builder()
                .amount(5)
                .category(Category.FOOD)
                .description("coupon1")
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(5)))
                .image("image.png")
                .price(55)
                .title("Food")
                .startDate(Date.valueOf(LocalDate.now()))
                .build();
        Coupon coupon2 = Coupon.builder()
                .amount(0)
                .category(Category.PC)
                .description("coupon2")
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .image("image.png")
                .price(1)
                .title("PC")
                .startDate(Date.valueOf(LocalDate.now().minusYears(1)))
                .build();

        Coupon coupon3 = Coupon.builder()
                .amount(100)
                .category(Category.FOOD)
                .description("coupon3")
                .endDate(Date.valueOf(LocalDate.now().minusDays(2)))
                .image("image.png")
                .price(9)
                .title("vacation")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
                .build();


        LoginManager loginManager = ctx.getBean(LoginManager.class);
        ClientService clientService = null;
        try {
            System.out.println("good login");
            clientService = loginManager.login("company1@cmp.com", "new", ClientType.Company);

               /*       System.out.println("bad login");
            clientService = loginManager.login("company@cmp.com", "1234", ClientType.Company);
*/
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        if (clientService instanceof CompanyService)
        {
            System.out.println("COMPANY!!!");
            ((CompanyService) clientService).addCoupon(coupon1);
            ((CompanyService) clientService).addCoupon(coupon2);
            ((CompanyService) clientService).addCoupon(coupon3);
            coupon1.setAmount(6);
            //coupon1.setDescription("coupon2");
            ((CompanyService) clientService).updateCoupon(coupon1);
            //((CompanyService) clientService).deleteCoupon(coupon1);



            Company a = ((CompanyService) clientService).getCompanyDetails();
            System.out.println("coupons:");


            System.out.println(a.getCoupons());
            System.out.println(((CompanyService) clientService).getCompanyDetails());



            System.out.println("get coupon by max PRice");
            System.out.println(((CompanyService) clientService).getCompanyCoupons(9));

            System.out.println("get coupon by category");
            System.out.println(((CompanyService) clientService).getCompanyCoupons(Category.FOOD));


        }


        System.out.println("EXIST BY EMAIL");
        System.out.print("existsFindByEmail:");
        System.out.println(companyRepository.existsFindByEmail("test1"));
        System.out.print("existsFindByEmail:");
        System.out.println(companyRepository.existsFindByEmail("company3@cmp.com"));

        System.out.println("FIND BY EMAIL");
        if (companyRepository.existsFindByEmail("company2@cmp.com")) {
            Company c = companyRepository.findByEmail("company2@cmp.com");
            System.out.println(c.toString());
        }

        System.out.print("existsFindByName:");
        System.out.println(companyRepository.existsFindByName("FirstCompany"));








/*
        System.out.println("bad login");

        try {
            clientService = loginManager.login("company1@cmp.com","password", ClientType.Company);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }
}
