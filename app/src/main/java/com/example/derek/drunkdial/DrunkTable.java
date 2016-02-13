package com.example.derek.drunkdial;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

/**
 * Created by juicedatom on 2/13/16.
 */
public class DrunkTable {

    private String endpoint;
    URLConnection yc = null;
    URL url = null;

    public DrunkTable(String endpoint) {
        this.endpoint = endpoint;
    }

    public JSONObject GET(String args) throws Exception {
        try {
            url = new URL(this.endpoint + args);
            yc = url.openConnection();
        } catch (Exception e) {
            System.err.println("Error on endpoint creation: " + e.getMessage());
        }

        BufferedReader reader  = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        return new JSONObject(reader.readLine());
    }

    public String getEndpoint() {
        return this.endpoint;
    }



}
