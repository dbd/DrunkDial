package com.example.derek.drunkdial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.derek.drunkdial.DrunkDatabase;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
    }

    public void submitInfo(View v) {

        String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
        String username = ((EditText)findViewById(R.id.editTextUsername)).getText().toString();
        String pass = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        String phone = ((EditText)findViewById(R.id.editTextPhonenumber)).getText().toString();

        System.out.println(DrunkDatabase.testAddUser(name, username, pass, phone));

        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}
  
