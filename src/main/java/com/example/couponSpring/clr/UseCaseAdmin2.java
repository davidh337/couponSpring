package com.example.couponSpring.clr;

import com.example.couponSpring.beans.Company;
import com.example.couponSpring.login.ClientType;
import com.example.couponSpring.login.LoginManager;
import com.example.couponSpring.services.AdminService;
import com.example.couponSpring.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(4)
@Component
@RequiredArgsConstructor
public class UseCaseAdmin2 implements CommandLineRunner {
    private final ApplicationContext ctx;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("ADMIN CASE 2 :delete Companies");

        LoginManager loginManager = ctx.getBean(LoginManager.class);
        ClientService clientService = null;

        try {
            clientService = loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if (clientService instanceof AdminService)
        {
            Company company = ((AdminService) clientService).getSingleCompany(1);
            System.out.println(company.getId());
            System.out.println(company.getCoupons());
            //((AdminService) clientService).deleteCompany(company);

        }

    }
}
