package com.example.couponSpring.jobs;

import com.example.couponSpring.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Transactional
public class CouponDelete {
    @Autowired
    private final CouponRepository couponRepository;

    private static final int DAY = 1000*60*60*24;
    @Scheduled(fixedRate = DAY)
    public void deleteOldsCoupons(){
        System.out.println("delete pag tokef!");
        try {
            couponRepository.deleteByEndDateBefore(Date.valueOf(LocalDate.now()));

        }
        catch (Exception e){
        System.out.println(e.getMessage());}
    }
}
