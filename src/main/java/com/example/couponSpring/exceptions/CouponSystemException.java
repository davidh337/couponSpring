package com.example.couponSpring.exceptions;

public class CouponSystemException extends Exception {

    public CouponSystemException(ErrMsg msg) {
        super(msg.getDescription());
    }
}
