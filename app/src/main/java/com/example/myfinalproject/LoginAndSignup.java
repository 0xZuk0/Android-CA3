package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginAndSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_signup);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_signup_frame_layout, new LoginInFragment()).addToBackStack(null).commit();
    }
}