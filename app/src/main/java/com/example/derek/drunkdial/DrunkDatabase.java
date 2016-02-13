package com.example.derek.drunkdial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

/**
 * Created by derek on 2/13/16.
 */
public class DrunkDatabase {

    static final String ec2 = "http://ec2-52-32-35-132.us-west-2.compute.amazonaws.com/";
    static final String oldtable = ec2 + "phpinfo.php";
    DrunkTable users = new DrunkTable(ec2 + "new.php");

    URL url = null;
    URLConnection yc = null;

    public DrunkDatabase() {
        doStuff();
    }

    public void doStuff() {
        try {
            //BufferedReader reader = users.GET("");
            //System.out.println("JOSH: " + reader.readLine());
        } catch (Exception e) {
            System.err.println("Failed on GET: " + e.getMessage());
        }
    }

    public SoberDriver getCurrentDriver() throws Exception{

        try {
            url = new URL(oldtable);
            URLConnection yc = url.openConnection();
        } catch (Exception e) {
            System.err.println("Error on endpoint creation: " + e.getMessage());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        System.out.println("omg");


        String inputLine = in.readLine();

        String[] data = new String[2];
        data = inputLine.split(",");
        System.out.printf("Name: %s Number: %s\n", data[0], data[1]);

        in.close();

        return new SoberDriver(data[0],data[1]);
    }

}
 
