package com.example.couponSpring.beans;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "company",cascade = CascadeType.PERSIST,orphanRemoval = true,fetch = FetchType.LAZY)
    @Singular
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private List<Coupon> coupons = new ArrayList<>();


}
