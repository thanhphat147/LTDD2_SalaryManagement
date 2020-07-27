package com.example.project.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.project.R;

public class SplashScreen extends AppCompatActivity {

    ImageView employerJumb, dongXuLMax, dongXuLMin, dongXuRMax, dongXuRMin, dongXuLMin2, dongXuRMin2;
    boolean jumb = true;
    Animation animDongXuL, animDongXuN, animDongXuN2;
    final long SPLASH_TIME_OUT = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setControl();
        setEvent();
    }

    private void setEvent() {
        animDongXuL = AnimationUtils.loadAnimation(this, R.anim.anim_dongxu1);
        animDongXuN = AnimationUtils.loadAnimation(this, R.anim.anim_dongxu2);
        animDongXuN2 = AnimationUtils.loadAnimation(this, R.anim.anim_dongxu3);
        dongXuLMax.startAnimation(animDongXuL);
        dongXuRMax.startAnimation(animDongXuL);
        dongXuLMin.startAnimation(animDongXuN);
        dongXuRMin.startAnimation(animDongXuN);
        dongXuLMin2.startAnimation(animDongXuN2);
        dongXuRMin2.startAnimation(animDongXuN2);
        final AnimationDrawable employer = (AnimationDrawable) employerJumb.getDrawable();
        employer.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivityChucNang.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void setControl() {
        employerJumb = findViewById(R.id.imgNV);
        dongXuLMax = findViewById(R.id.imgMNLeftMax);
        dongXuLMin = findViewById(R.id.imgMNLeftMin);
        dongXuRMax = findViewById(R.id.imgMNRightMax);
        dongXuRMin = findViewById(R.id.imgMNRightMin);
        dongXuLMin2 = findViewById(R.id.imgMNLeftMin2);
        dongXuRMin2 = findViewById(R.id.imgMNRightMin2);
    }
}