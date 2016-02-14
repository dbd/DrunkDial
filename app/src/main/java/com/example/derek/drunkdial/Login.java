package com.example.derek.drunkdial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {

        DrunkDatabase dd = new DrunkDatabase();
        if (dd.validateCredentials( ((EditText) findViewById(R.id.loginname)).getText().toString() ,
                ((EditText)findViewById(R.id.loginpass)).getText().toString())) {
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        } else {
            TextView tv = (TextView) findViewById(R.id.textView6);
            tv.setText("Invalid Credentials");
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        }
    }

    public void startCreate(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
