package com.example.derek.drunkdial;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by derek on 2/13/16.
 */
public class DrunkDatabase {

    static final String ec2 = "http://ec2-52-32-35-132.us-west-2.compute.amazonaws.com/";
    static final String oldtable = ec2 + "phpinfo.php";

    DrunkResource users = new DrunkResource(ec2 + "new.php");

    URL url = null;
    URLConnection yc = null;

    public DrunkDatabase() {
        ArrayList<DrunkUser> ret = getUsers();
        for (int i=0; i<ret.size(); ++i) {
            System.out.println("USER: " + ret.get(i));
        }
    }

    boolean validateCredentials(String username, String password) {
        DrunkResource validate = new DrunkResource(ec2, "validate.php");

        JSONObject ret;
        try {
            HashMap<String,String> hashy = new HashMap<String,String>();
            hashy.put("user_login", username); hashy.put("user_pass", password);
            ret = validate.USE(hashy);

            return (ret.get("valid").equals("1"));
        } catch (Exception e) {
            System.err.println("Error trying to validate credentials" + e.getMessage());
        }

        return false;
    }

    void testAddUser(String name) {
        DrunkResource addtest = new DrunkResource(ec2,"addmember.php");

        HashMap<String,String> args = new HashMap<String,String>();
        args.put("name",name);

        try {
            addtest.USE(args);
        } catch (Exception e) {
            System.err.println("Failed to add test user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void doStuff() {
        try {
            JSONObject hi = users.USE();

            JSONObject stuff = hi.getJSONObject("members");
            System.out.println("SOMETHINGCOOL: " + stuff.getJSONObject("1").get("name"));

        } catch (Exception e) {
            System.err.println("Failed on GET: " + e.getMessage());
        }
    }

    public ArrayList<DrunkUser> getUsers() {
        return getUsers(null);
    }

    public ArrayList<DrunkUser> getUsers(Boolean active) {
        DrunkResource getUsers = new DrunkResource(ec2,"getusers.php");

        ArrayList<DrunkUser> ret = new ArrayList<DrunkUser>();
        JSONObject json = null;
        int n = 0;
        HashMap<String,String> args = new HashMap<String,String>();

        if (active != null) {
            args.put("active", active ? "1" : "0");
        }

        try {
            json = getUsers.USE(args);
            n = json.getInt("num users");
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i=1; i<=n; ++i) {

            try {
                JSONObject jsonPerson = json.getJSONObject("users").getJSONObject(Integer.toString(i));
                ret.add(new DrunkUser(jsonPerson));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return ret;

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
 
