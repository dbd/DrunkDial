package com.example.derek.drunkdial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.*;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    //SoberDriver driver = new SoberDriver("Joshua Manela", "2483967666");
    SoberDriver driver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView nameText = (TextView)findViewById(R.id.soberName);
        final TextView numberText = (TextView)findViewById(R.id.soberPhone);
        final Button butt = (Button) findViewById(R.id.button);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            driver = getCurrentDriver();
            drawDriver(driver, nameText, numberText);
        } catch (Exception e) {
            System.out.println("Done");
        }

        DrunkDatabase data = new DrunkDatabase();

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    call(driver);
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        });


    }

    private void drawDriver(SoberDriver driver, TextView nameText, TextView numberText) {
        nameText.setText(driver.getName());
        numberText.setText(driver.getNumber());
    }

    private void call(SoberDriver driver) {
        System.out.println("hello world!");
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + driver.getNumber()));

            if ( ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        11);
            }

            startActivity(callIntent);
        } catch (Exception e) {
            System.out.println("Shit's fucked, yo.");
        }
    }

    public SoberDriver getCurrentDriver() throws Exception{

        String link = "http://ec2-52-32-35-132.us-west-2.compute.amazonaws.com/phpinfo.php";
        URL url = new URL(link);

        URLConnection yc = url.openConnection();
        System.out.println("heredaf");

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
  
