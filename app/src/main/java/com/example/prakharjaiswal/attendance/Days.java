package com.example.prakharjaiswal.attendance;

/**
 * Created by Prakhar Jaiswal on 21-06-2017.
 */

public class Days {
    String date;
    boolean status;

    public Days(){

    }

    public Days(String date, boolean status){

        this.date = date;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }
}
