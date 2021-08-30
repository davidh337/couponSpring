package com.example.couponSpring.exceptions;

public enum ErrMsg {
    COMPANY_NAME_EXIST ("company name is already used"),
    COMPANY_EMAIL_EXIST("company email is already used"),
    ID_NOT_FOUND ("id is not used"),
    CUSTOMER_EMAIL_EXIST("customer email is already used"),
    LOGIN_ERROR("email or password not good"),
    COUPON_ALREADY_BUY("Coupon already purshased!"),
    NO_COUPON_AVAIABLE("No coupons avaiable, please choose an another coupon"),
    COUPON_EXIST("coupon already Exist, choose another description")
    ;



    private  String description;
    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
