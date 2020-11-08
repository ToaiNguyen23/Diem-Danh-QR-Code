package com.example.maqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Intro1Activity extends AppCompatActivity {


    Animation topAnim;
    ImageView image;

    private static int time_inntro = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAGS_CHANGED);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        image = (ImageView) findViewById(R.id.imgview);

        image.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intro1Activity.this,Intro2Activity.class);
                startActivity(intent);
                finish();
            }
        },time_inntro);
    }
}