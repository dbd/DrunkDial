package com.example.derek.drunkdial;

import org.json.JSONObject;

/**
 * Created by juicedatom on 2/13/16.
 */
public class DrunkUser {

    private int id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private boolean active;
    private int[] gps = new int[2];

    public DrunkUser (int id, String name, String phone, Boolean active, int gps[],
        String username, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.username = username;
        this.password = password;
    }

    public DrunkUser (JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.name = (String) json.get("name");
            this.phone = (String) json.get("phone");
            this.name = (String) json.get("user_login");
            this.phone = (String) json.get("user_pass");
            this.active = json.getBoolean("active");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.getName();
    }

    public String getPhone() {
        return this.getPhone();
    }

    @Override
    public String toString() {
        return String.format("%d %s", this.id, this.name);
    }

}
