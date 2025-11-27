package com.airBnb.airBnbProject.exception;

public class HotelNotFoundException extends RuntimeException{

    public HotelNotFoundException(String message){
        super(message);
    }
}
