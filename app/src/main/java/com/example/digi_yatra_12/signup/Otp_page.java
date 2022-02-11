package com.example.digi_yatra_12.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.GlobalApplication;
import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.navbar.Navbar_main;
import com.example.util.MyHandler;


public class Otp_page extends  AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        home = findViewById(R.id.SubmitBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Otp_page.this, Navbar_main.class));
            }
        });
    }
}