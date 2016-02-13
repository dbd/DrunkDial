package com.example.derek.drunkdial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button butt = (Button) findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("2483967666");
            }
        });
    }

    private void call(String number) {
        System.out.println("hello world!");
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));

            if ( ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        11);
            }

            startActivity(callIntent);
        } catch (Exception e) {
            System.out.println("Shit's fucked, yo.");
        }
    }
}
