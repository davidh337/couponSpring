package com.example.couponSpring.beans;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
//@Data
@Getter
@Setter
@Builder
//@ToString

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="company_id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;



    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", company=" + company.getId() +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
