package com.cite.kidpick;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameCategory extends AppCompatActivity {
    Animation scaleUp;
    Animation scaleDown;
    RelativeLayout alphabets,numbers,shapes,colors,fruits,vegetables;

    private List<Integer> imageToPass;
    MediaPlayer click;

    ImageView cloud_top,cloud_mid,cloud_bot;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_category);
        DisplayMetrics displayMetrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;

        cloud_top = findViewById(R.id.cloud_top);
        cloud_mid = findViewById(R.id.cloud_mid);
        cloud_bot = findViewById(R.id.cloud_bot);

        ViewGroup.LayoutParams layoutParams = cloud_top.getLayoutParams();
        int ivWidth = layoutParams.width;
        int newWidth = width - ivWidth;
        // Toast.makeText(this, String.valueOf(newWidth), Toast.LENGTH_SHORT).show();

        TranslateAnimation cloud_movingT = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, newWidth,
                Animation.ABSOLUTE, -80,
                Animation.ABSOLUTE, -80
        );
        TranslateAnimation cloud_movingM = new TranslateAnimation(
                Animation.ABSOLUTE, 900,
                Animation.ABSOLUTE, -400,
                Animation.ABSOLUTE, 700,
                Animation.ABSOLUTE, 700
        );

        TranslateAnimation cloud_movingB = new TranslateAnimation(
                Animation.ABSOLUTE, 800,
                Animation.ABSOLUTE, -400,
                Animation.ABSOLUTE, 1200,
                Animation.ABSOLUTE, 1200
        );

        cloud_movingT.setDuration(2000);
        cloud_movingT.setFillAfter(true);
        cloud_movingT.setStartOffset(0);
        cloud_movingT.setRepeatCount(Animation.INFINITE);
        cloud_movingT.setRepeatMode(Animation.REVERSE);
        cloud_top.startAnimation(cloud_movingT);

        cloud_movingM.setDuration(5000);
        cloud_movingM.setFillAfter(true);
        cloud_movingM.setStartOffset(1000);
        cloud_movingM.setRepeatCount(Animation.INFINITE);
        cloud_movingM.setRepeatMode(Animation.REVERSE);
        cloud_mid.startAnimation(cloud_movingM);

        cloud_movingB.setDuration(4000);
        cloud_movingB.setFillAfter(true);
        cloud_movingB.setStartOffset(1000);
        cloud_movingB.setRepeatCount(Animation.INFINITE);
        cloud_movingB.setRepeatMode(Animation.REVERSE);
        cloud_bot.startAnimation(cloud_movingB);


        imageToPass = new ArrayList<>();

        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down);

        alphabets = findViewById(R.id.alphabets);
        numbers = findViewById(R.id.numbers);
        shapes = findViewById(R.id.shapes);
        colors = findViewById(R.id.colors);
        fruits = findViewById(R.id.fruits);
        vegetables = findViewById(R.id.vegetables);


        alphabets.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                alphabets.startAnimation(scaleUp);
                // alphabets.animate().scaleXBy(1.2f).setDuration(100).start();
                return true;
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                click = MediaPlayer.create(this,R.raw.btn_klik);
                click.start();
                //alphabets.animate().scaleXBy(1.0f).setDuration(100).start();
                alphabets.startAnimation(scaleDown);
                loadCardsAlphabets();
                return true;

            }
            return false;
        });
        numbers.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                numbers.startAnimation(scaleUp);
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                click = MediaPlayer.create(this,R.raw.btn_klik);
                click.start();
                numbers.startAnimation(scaleDown);
                loadCardsNumbers();
               /* Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                startActivity(intent);*/
            }
            return true;
        });

        colors.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                colors.startAnimation(scaleUp);
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                click = MediaPlayer.create(this,R.raw.btn_klik);
                click.start();
                colors.startAnimation(scaleDown);
                loadCardsColors();
              /*  Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                startActivity(intent);*/
            }
            return true;
        });

        shapes.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                shapes.startAnimation(scaleUp);
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                click = MediaPlayer.create(this,R.raw.btn_klik);
                click.start();
                shapes.startAnimation(scaleDown);
                loadCardsShapes();
              /*  Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                startActivity(intent);*/
            }
            return true;
        });



        fruits.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                fruits.startAnimation(scaleUp);
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                click = MediaPlayer.create(this,R.raw.btn_klik);
                click.start();
                fruits.startAnimation(scaleDown);
                loadCardsFruits();
              /*  Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                startActivity(intent);*/
            }
            return true;
        });
        vegetables.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                vegetables.startAnimation(scaleUp);
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                click = MediaPlayer.create(this,R.raw.btn_klik);
                click.start();
                vegetables.startAnimation(scaleDown);
                loadCardsVegetables();
              /*  Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                startActivity(intent);*/
            }
            return true;
        });

    }

    private void loadCardsVegetables(){
        imageToPass.add(R.drawable.bottle_gourd);
        imageToPass.add(R.drawable.cabbage);
        imageToPass.add(R.drawable.carrots);
        imageToPass.add(R.drawable.cocumber);
        imageToPass.add(R.drawable.corn);
        imageToPass.add(R.drawable.eggplant);
        imageToPass.add(R.drawable.garlic);
        imageToPass.add(R.drawable.ginger);
        imageToPass.add(R.drawable.onion);
        imageToPass.add(R.drawable.pepper);



        Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
        intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
        intent.putExtra("CATEGORY","Vegetables");
        startActivity(intent);
        Animatoo.animateSplit(this);
    
    }


    private void loadCardsFruits(){
        imageToPass.add(R.drawable.apple);
        imageToPass.add(R.drawable.avocado);
        imageToPass.add(R.drawable.banana);
        imageToPass.add(R.drawable.grapes);
        imageToPass.add(R.drawable.guava);
        imageToPass.add(R.drawable.lemon);
        imageToPass.add(R.drawable.orange);
        imageToPass.add(R.drawable.pineapple);
        imageToPass.add(R.drawable.strawberry);
        imageToPass.add(R.drawable.watermelon);




        Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
        intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
        intent.putExtra("CATEGORY","Fruits");
        startActivity(intent);
        Animatoo.animateSplit(this);

    }

    private void loadCardsNumbers() {
        imageToPass.add(R.drawable.one);
        imageToPass.add(R.drawable.two);
        imageToPass.add(R.drawable.three);
        imageToPass.add(R.drawable.four);
        imageToPass.add(R.drawable.five);
        imageToPass.add(R.drawable.six);
        imageToPass.add(R.drawable.seven);
        imageToPass.add(R.drawable.eight);
        imageToPass.add(R.drawable.nine);
        imageToPass.add(R.drawable.ten);



        Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
        intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
        intent.putExtra("CATEGORY","numbers");
        startActivity(intent);
        Animatoo.animateSplit(this);


    }


    private void loadCardsAlphabets() {

        //add items to arraylist
        imageToPass.add(R.drawable.ic_a);
        imageToPass.add(R.drawable.ic_b);
        imageToPass.add(R.drawable.ic_c);
        imageToPass.add(R.drawable.ic_d);
        imageToPass.add(R.drawable.ic_e);
        imageToPass.add(R.drawable.ic_f);
        imageToPass.add(R.drawable.ic_g);
        imageToPass.add(R.drawable.ic_h);
        imageToPass.add(R.drawable.ic_i);
        imageToPass.add(R.drawable.ic_j);


        Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
        intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
        intent.putExtra("CATEGORY","Alphabets");
        startActivity(intent);
        Animatoo.animateSplit(this);
    }

    private void loadCardsColors(){
        imageToPass.add(R.drawable.ic_black);
        imageToPass.add(R.drawable.ic_white);
        imageToPass.add(R.drawable.ic_red);
        imageToPass.add(R.drawable.ic_green);
        imageToPass.add(R.drawable.ic_yellow);
        imageToPass.add(R.drawable.ic_blue);
        imageToPass.add(R.drawable.ic_pink);
        imageToPass.add(R.drawable.ic_orange);
        imageToPass.add(R.drawable.ic_purple);
        imageToPass.add(R.drawable.ic_brown);



        Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
        intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
        intent.putExtra("CATEGORY","Colors");
        startActivity(intent);
        Animatoo.animateSplit(this);

    }

    private void loadCardsShapes(){
        imageToPass.add(R.drawable.hexagon);
        imageToPass.add(R.drawable.rhombus);
        imageToPass.add(R.drawable.star);
        imageToPass.add(R.drawable.trapezoid);
        imageToPass.add(R.drawable.arrow);
        imageToPass.add(R.drawable.circle);
        imageToPass.add(R.drawable.ellipse);
        imageToPass.add(R.drawable.heart);
        imageToPass.add(R.drawable.triangle);
        imageToPass.add(R.drawable.square);



        Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
        intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
        intent.putExtra("CATEGORY","shapes");
        startActivity(intent);
        Animatoo.animateSplit(this);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameCategory.this,HomeScreen.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
        finishAffinity();
    }
}