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
import android.widget.EditText;
import android.widget.TextView;



import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SoberDriver driver = new SoberDriver("Joshua Manela", "2483967666");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView nameText = (TextView)findViewById(R.id.soberName);
        final TextView numberText = (TextView)findViewById(R.id.soberPhone);
        final Button butt = (Button) findViewById(R.id.button);

        drawDriver(nameText, numberText);
        DrunkDatabase data = new DrunkDatabase();

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(driver);
            }
        });
    }

    private void drawDriver(TextView nameText, TextView numberText) {
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
}
