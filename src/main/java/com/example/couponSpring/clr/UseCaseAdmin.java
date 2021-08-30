package com.example.couponSpring.clr;

import com.example.couponSpring.beans.Company;
import com.example.couponSpring.beans.Customer;
import com.example.couponSpring.login.ClientType;
import com.example.couponSpring.login.LoginManager;
import com.example.couponSpring.services.AdminService;
import com.example.couponSpring.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(2)
@Component
@RequiredArgsConstructor
public class UseCaseAdmin implements CommandLineRunner {
    private final ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ADMIN CASE:companies");

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
           // ((AdminService) clientService).deleteCompany(1);
           // ((AdminService) clientService).deleteCompany(2);
           // ((AdminService) clientService).deleteCompany(3);

            List<Company> companies = ((AdminService) clientService).getAllCompanies();
            if (companies.size()==0)
            {
                System.out.println("NO COMPANIES");
            }
            else   {
                for (Company c:companies)
                {
                    System.out.println(c);
                }
            }
            Company company1 = Company.builder()
                    .email("company1@cmp.com")
                    .name("FirstCompany")
                    .password("1234")
                    .build();
            Company company2 = Company.builder()
                    .email("company2@cmp.com")
                    .name("SecondCompany")
                    .password("password")
                    .build();
            Company company3 = Company.builder()
                    .email("company3@cmp.com")
                    .name("3Company")
                    .password("hahaha")
                    .build();
            ((AdminService) clientService).addCompany(company1);
            ((AdminService) clientService).addCompany(company2);
            ((AdminService) clientService).addCompany(company3);
            System.out.println("Get Single company");
            Company getC= ((AdminService) clientService).getSingleCompany(1);
            System.out.println(getC);

            //getC.setName("SecondCompany");
            getC.setPassword("new");
            ((AdminService) clientService).updateCompany(getC);

            System.out.println("Get Single company after update");

            System.out.println(((AdminService) clientService).getSingleCompany(1));
            /*
            System.out.println("delete company");
             ((AdminService) clientService).deleteCompany(1);
*/

            /* ((AdminService) clientService).deleteCompany(2);
             ((AdminService) clientService).deleteCompany(3);*/


            System.out.println("ADMIN CASE:customers");



            Customer customer1 = Customer.builder()
                    .firstName("david")
                    .lastName("haccoun")
                    .email("david@mail.com")
                    .password("dada")
                    .build();
            Customer customer2 = Customer.builder()
                    .firstName("itai")
                    .lastName("cohen")
                    .email("itai@mail.com")
                    .password("iitit")
                    .build();
            Customer customer3 = Customer.builder()
                    .firstName("yudit")
                    .lastName("keke")
                    .email("yudith@mail.com")
                    .password("yuyu")
                    .build();


            ((AdminService) clientService).addCustomer(customer1);
            ((AdminService) clientService).addCustomer(customer2);
            ((AdminService) clientService).addCustomer(customer3);


            Customer customerCustom = ((AdminService) clientService).getSingleCustomer(1);
            customerCustom.setPassword("pppp");
            ///customerCustom.setEmail("itai@mail.com");
            ((AdminService) clientService).updateCustomer(customerCustom);

            List<Customer> customers = ((AdminService) clientService).getAllCustomers();

            System.out.println("list of customers:");
            for (Customer c: customers) {
                System.out.println(c);
            }




        }
    }
}
