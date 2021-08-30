package com.example.couponSpring.login;

import com.example.couponSpring.exceptions.CouponSystemException;
import com.example.couponSpring.services.AdminService;
import com.example.couponSpring.services.ClientService;
import com.example.couponSpring.services.CompanyService;
import com.example.couponSpring.services.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope("singleton")
@Lazy
@Data
public class LoginManager {
    private final ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        ClientService clientService = null;

        switch (clientType)
        {
            case Administrator:
                clientService= (ClientService) ctx.getBean(AdminService.class);
                break;
            case Company:
                clientService= (ClientService) ctx.getBean(CompanyService.class);
                break;
            case Customer:
                clientService= (ClientService) ctx.getBean(CustomerService.class);
                break;
        }

        clientService.login(email,password);

        return  clientService;
    }
}
