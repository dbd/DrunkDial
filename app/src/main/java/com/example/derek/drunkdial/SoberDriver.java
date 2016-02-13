package com.example.derek.drunkdial;

/**
 * Created by derek on 2/13/16.
 */
public class SoberDriver {

    private String name = "";
    private String number = "";

    public SoberDriver(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getStrippedNumber() {
        return number.replaceAll("-", "");
    }

}
 
