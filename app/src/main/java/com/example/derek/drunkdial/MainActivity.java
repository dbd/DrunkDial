package com.example.derek.drunkdial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
    }

    public void submitInfo(View v) {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}
  
