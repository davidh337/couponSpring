package com.example.couponSpring.repos;

import com.example.couponSpring.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

        boolean existsFindByEmail(String email);
        boolean existsFindByName(String name);

        @Override
        boolean existsById(Integer integer);


        Company findByEmail(String email);
        Company findByName(String name);
        void deleteById(int ID);

}
