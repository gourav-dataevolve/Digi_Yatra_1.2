package com.example.digi_yatra_12.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.fragments.WalletFragment;

public class MainActivity extends AppCompatActivity {
FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frame_container);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new WalletFragment()).commit();
    }
}