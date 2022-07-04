package com.cite.kidpick;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    TextView kid;
    Animation kiddy,shake;
    RelativeLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);






        logo = findViewById(R.id.logo);
        kid = findViewById(R.id.kid);
        shake = AnimationUtils.loadAnimation(this,R.anim.rotate);
        kid.startAnimation(shake);
        container = findViewById(R.id.container);
        new Handler().postDelayed(() -> animatefall(),500);


    }

    private void animatefall() {
        float newY = this.kid.getY()- this.logo.getHeight()-10;
        ViewPropertyAnimator animator = this.logo.animate()
                .translationY(newY)
                .setInterpolator( new AccelerateInterpolator())
                .setInterpolator(new BounceInterpolator())
                .setDuration(2000);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                container.setBackgroundColor(Color.parseColor("#2FA4FF"));
                kid.clearAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, HomeScreen.class);
                        startService(new Intent(SplashScreen.this, BackgroundSoundService.class));
                        startActivity(intent);
                        finishAffinity();

                    }
                },1000);

            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }
}