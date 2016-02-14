package com.example.derek.drunkdial;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.HashMap;


public class DrunkResource {

    private String endpoint;
    URLConnection yc = null;
    URL url = null;

    public DrunkResource(String endpoint) {
        this.endpoint = endpoint;
    }

    public DrunkResource(String base, String exten) {
        this.endpoint = base + exten;
    }

    public JSONObject USE() throws Exception {
        return USE(new HashMap<String, String>());
    }

    public JSONObject USE(HashMap<String,String> args) throws Exception {
        String tmp = "";

        for (String key : args.keySet()) {
            tmp += String.format("%s=%s&", key, args.get(key));
        }

        tmp = String.format("%s?%s", this.endpoint, tmp);

        tmp = tmp.substring(0, tmp.length() - 1);

        System.out.printf("I am going to: %s\n", tmp);


        try {
            url = new URL(tmp);
            System.out.println("Debugging 0.4");
            yc = url.openConnection();
            System.out.println("DEbugging 0.6");
            InputStream inputStream = null;
            System.out.println("debuggin 0.7");
            inputStream = yc.getInputStream();
            System.out.println("Deguggin 0.8");
        } catch (Exception e) {
            System.err.println("Error on endpoint creation: " + e.getMessage());
        }

        System.out.println("Debugging 1.25");
        InputStreamReader isr = new InputStreamReader(yc.getInputStream());
        System.out.println("Debugging 1.50");
        BufferedReader reader  = new BufferedReader(isr);
        System.out.println("Debugging 2");

        String retStr = reader.readLine();
        System.out.println("returned string: " + retStr);

        return new JSONObject(retStr);
    }

    public String getEndpoint() {
        return this.endpoint;
    }



}
