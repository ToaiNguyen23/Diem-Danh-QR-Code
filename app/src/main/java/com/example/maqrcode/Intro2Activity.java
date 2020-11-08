package com.example.maqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Intro2Activity extends AppCompatActivity {
    private static int time_inntro = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intro2Activity.this,DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        },time_inntro);
    }
}