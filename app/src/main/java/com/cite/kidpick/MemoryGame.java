package com.cite.kidpick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MemoryGame extends AppCompatActivity {
    public static  final String COLOR_MED = "colMedium";
    public static  final String COLOR_HARD = "colHard";
    private MediaPlayer cardClosed,cardClick,matchedSfx,unmatchedSfx,loseSfx,winSfx;
    MediaPlayer mp;
    String categoryLevel;
    Toast toast;
    KonfettiView celebration;


    List<Integer> images;
    List<Integer> pos;
    List<Integer> itemMatched;
    int index = 0;
    int width;
    int height;
    int timeDelay;
    CountDownTimer countDownTimer;
    TextView time,score;
    int currentPos = -1;

    ImageView curView = null;
    private int countPair = 0;

    GridView gridView;
    ImageAdapter imageAdapter;
    int column = 3;
    int points = 0,currentPoints=0;

    String difficulty;
    Animation shake;

    public static boolean mediumUnlocked;
    public static boolean hardUnlocked;
    LinearLayout winpop;
    Button winUnlock;

    ImageView cloud_top,cloud_mid,cloud_bot,birdy;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        points = getIntent().getIntExtra("SAVE_POINTS",0);
         toast =  Toast.makeText(this, "Cards Closed!!Good Luck!", Toast.LENGTH_SHORT);

        cloud_top = findViewById(R.id.cloud_top);
        cloud_mid = findViewById(R.id.cloud_mid);
        cloud_bot = findViewById(R.id.cloud_bot);
        birdy = findViewById(R.id.birds);
        birdy.setVisibility(View.GONE);
        Glide.with(this).load(R.drawable.birdy).into(birdy);

        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
                mediaPlayer.release();
                cardClick = null;

            }
        };

        TranslateAnimation cloud_movingT = new TranslateAnimation(
                Animation.ABSOLUTE, 1000,
                Animation.ABSOLUTE, -1000,
                Animation.ABSOLUTE, 200,
                Animation.ABSOLUTE, 200
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

        cloud_movingT.setDuration(6000);
        cloud_movingT.setFillAfter(true);
        cloud_movingT.setStartOffset(900);
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





        cardClosed=MediaPlayer.create(MemoryGame.this,R.raw.closecards_sfx);
//        unmatchedSfx=MediaPlayer.create(MemoryGame.this,R.raw.unmatched_sfx);
//        loseSfx=MediaPlayer.create(MemoryGame.this,R.raw.gameover_sfx);
//        winSfx=MediaPlayer.create(MemoryGame.this,R.raw.wingame_sfx);

        itemMatched = new ArrayList<Integer>();
        time = findViewById(R.id.timer);
        score = findViewById(R.id.score);
        winpop = findViewById(R.id.win_popup);
        celebration = findViewById(R.id.celebrationView);

        shake = AnimationUtils.loadAnimation(this,R.anim.rotate);

        images = (ArrayList<Integer>)getIntent().getSerializableExtra("IMAGES");
         difficulty = getIntent().getStringExtra("DIFFICULTY");
        categoryLevel = getIntent().getStringExtra("CATEGORYLEVEL");


        if (difficulty.equalsIgnoreCase("easy")){
            birdy.setVisibility(View.VISIBLE);
            column = 3;
            width = 200;
            height = 250;
            timeDelay =4000;

        }else if (difficulty.equalsIgnoreCase("medium")){
            column = 3;
            width = 180;
            height = 230;
            timeDelay =5000;
        }else if (difficulty.equalsIgnoreCase("hard")){
            column = 4;
            width = 150;
            height = 200;
            timeDelay =6000;
        }
//set add position relative to image
        pos = new ArrayList<Integer>();
        for (int i = 0; i < images.size()/2; i++){
            pos.add(i);
        }
//duplicate each position index
        pos.addAll(pos);
//shuffle position of images
        Collections.shuffle(pos);

        gridView = findViewById(R.id.grid_view);
        gridView.setNumColumns(column);
        imageAdapter = new ImageAdapter(this,images,pos,width,height,timeDelay,cardClosed);

        countDownTimer = new CountDownTimer(timeDelay,1000) {
            @Override
            public void onTick(long l) {
                update((int) (l/1000));
            }

            @Override
            public void onFinish() {
                toast.show();
            }
        }.start();

        gridView.setAdapter(imageAdapter);
        gridView.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gridView.setEnabled(true);
            }
        },timeDelay);

        gridView.setOnItemClickListener((adapterView, view, position, l) -> {
            if (cardClick == null){
                cardClick = MediaPlayer.create(this,R.raw.opencard_sfx);
            }
            cardClick.start();
            cardClick.setOnCompletionListener(onCompletionListener);






            if (currentPos < 0){
                if (itemMatched.contains(pos.get(position))){
                    Toast.makeText(this, "Select other cards", Toast.LENGTH_SHORT).show();
                }else {
                    currentPos = position;
                    curView = (ImageView) view;
                    Glide.with(MemoryGame.this).load(R.drawable.flip2).into(curView);
                    new Handler().postDelayed(() -> {
                        ((ImageView) view).setBackgroundResource(R.drawable.image_border);
                        ((ImageView) view).setImageResource(images.get(pos.get(position)));

                    },400);
                }
            }else {
                if (currentPos == position){
                    // ((ImageView) view).setImageResource(R.drawable.blue2);
                    Glide.with(MemoryGame.this).load(R.drawable.flip2).into((ImageView) view);
                    new Handler().postDelayed(() -> {
                        view.setBackgroundResource(R.color.trans);
                        ((ImageView) view).setImageResource(R.drawable.blue2);

                    },400);

                }else if (pos.get(currentPos) != pos.get(position)){
                    gridView.setEnabled(false);
                    if (itemMatched.contains(pos.get(position))){

                            Glide.with(MemoryGame.this).load(R.drawable.blue2).into(curView);
                            curView.setBackgroundResource(R.color.trans);
                            Toast.makeText(this, "Select other cards", Toast.LENGTH_SHORT).show();


                        gridView.setEnabled(true);
                    }else{
                        notMatched(view,position);
                    }

                }else {
                    gridView.setEnabled(false);
                    if (itemMatched.contains(pos.get(position))){
                        Glide.with(MemoryGame.this).load(R.drawable.blue2).into(curView);
                        Toast.makeText(this, "Select other cards", Toast.LENGTH_SHORT).show();
                        gridView.setEnabled(true);
                    }else {
                        matchedCards(view,position,currentPos);
                    }

                }
                currentPos = -1;
            }

            cardClick = null;

            if (cardClick != null){
                cardClick.stop();
                cardClick = null;
            }



        });

    }

    private void notMatched(View view, int position){
        if (unmatchedSfx == null){
            unmatchedSfx=MediaPlayer.create(MemoryGame.this,R.raw.unmatched_sfx);
        }



        Glide.with(MemoryGame.this).load(R.drawable.flip2).into((ImageView) view);
        new Handler().postDelayed(() -> {
            view.setBackgroundResource(R.drawable.image_border);
            ((ImageView)view).setImageResource(images.get(pos.get(position)));
            new Handler().postDelayed(() -> {
                view.setBackgroundResource(R.color.trans);
                curView.setBackgroundResource(R.color.trans);
                curView.setImageResource(R.drawable.blue2);
                ((ImageView)view).setImageResource(R.drawable.blue2);
                unmatchedSfx.start();

                gridView.setEnabled(true);
                unmatchedSfx = null;

            },500);
        },500);



    }

    private void matchedCards(View view, int position,int currentPos){
        if (matchedSfx == null){
            matchedSfx=MediaPlayer.create(MemoryGame.this,R.raw.matchedcards_sfx);
        }


        Glide.with(MemoryGame.this).load(R.drawable.flip2).into((ImageView) view);
        new Handler().postDelayed(() -> {
            ((ImageView) view).setBackgroundResource(R.drawable.image_border);
            ((ImageView) view).setImageResource(images.get(pos.get(position)));
            itemMatched.add(pos.get(position));
            matchedSfx.start();


            countPair++;
            gridView.setEnabled(true);
            String scorePoints = String.valueOf(countPair);
            score.setText(scorePoints);
            score.startAnimation(shake);
            if (countPair == images.size()/2){
                winGame(countPair);
            }
            matchedSfx = null;

        },500);

    }

    private void winGame(int countPair) {
        if (winSfx == null){
            winSfx = MediaPlayer.create(this,R.raw.wingame_sfx);

        }
        currentPoints = countPair;

        celebration.build()
                .addColors(Color.RED,Color.YELLOW,Color.MAGENTA,Color.WHITE,Color.GREEN)
                .setDirection(0.0,359)
                .setSpeed(1f,3f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT,Shape.CIRCLE)
                .addSizes(new Size(12,5))
                .setPosition(-50f,celebration.getWidth()+50f,-50f,-50f)
                .streamFor(300,3000L);
        WhatCategory();

        gridView.setEnabled(false);
        gridView.setClickable(false);




    }

    private void WhatCategory() {

        SharedPreferences.Editor editor1 = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
        if (categoryLevel.toLowerCase().contains("alphabets")){

            if (!GameDifficulty.alphaEasyDone && categoryLevel.equalsIgnoreCase("alphabetseasy")){
                 points = points + currentPoints;
                GameDifficulty.alphaEasyDone = true;
                 GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.alphaMedDone && categoryLevel.equalsIgnoreCase("alphabetsmedium")){
                points = points + currentPoints;
                GameDifficulty.alphaMedDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.alphaHardDone && categoryLevel.equalsIgnoreCase("alphabetshard")){
                points = points + currentPoints;
                GameDifficulty.alphaHardDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else {
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }

        }else if (categoryLevel.toLowerCase().contains("numbers")){

            if (!GameDifficulty.numEasyDone && categoryLevel.equalsIgnoreCase("numberseasy")){
                points = points + currentPoints;
                GameDifficulty.numEasyDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.numMedDone && categoryLevel.equalsIgnoreCase("numbersmedium")){
                points = points + currentPoints;
                GameDifficulty.numMedDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.numhaHardDone && categoryLevel.equalsIgnoreCase("numbershard")){
                points = points + currentPoints;
                GameDifficulty.numhaHardDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else {
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }

        }else if (categoryLevel.toLowerCase().contains("shapes")){

            if (!GameDifficulty.shapeEasyDone && categoryLevel.equalsIgnoreCase("shapeseasy")){
                points = points + currentPoints;
                GameDifficulty.shapeEasyDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.shapeMedDone && categoryLevel.equalsIgnoreCase("shapesmedium")){
                points = points + currentPoints;
                GameDifficulty.shapeMedDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.shapeHardDone && categoryLevel.equalsIgnoreCase("shapeshard")){
                points = points + currentPoints;
                GameDifficulty.shapeHardDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else {
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }

        }else if (categoryLevel.toLowerCase().contains("colors")){

            if (!GameDifficulty.colorEasyDone && categoryLevel.equalsIgnoreCase("colorseasy")){
                points = points + currentPoints;
                GameDifficulty.colorEasyDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.colorMedDone && categoryLevel.equalsIgnoreCase("colorsmedium")){
                points = points + currentPoints;
                GameDifficulty.colorMedDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.colorHardDone && categoryLevel.equalsIgnoreCase("colorshard")){
                points = points + currentPoints;
                GameDifficulty.colorHardDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else {
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }

        }else if (categoryLevel.toLowerCase().contains("fruits")){

            if (!GameDifficulty.fruitEasyDone && categoryLevel.equalsIgnoreCase("fruitseasy")){
                points = points + currentPoints;
                GameDifficulty.fruitEasyDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.fruitMedDone && categoryLevel.equalsIgnoreCase("fruitsmedium")){
                points = points + currentPoints;
                GameDifficulty.fruitMedDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.fruitHardDone && categoryLevel.equalsIgnoreCase("fruitshard")){
                points = points + currentPoints;
                GameDifficulty.fruitHardDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else {
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }
        }else if (categoryLevel.toLowerCase().contains("vegetables")){

            if (!GameDifficulty.vegeEasyDone && categoryLevel.equalsIgnoreCase("vegetableseasy")){
                points = points + currentPoints;
                GameDifficulty.vegeEasyDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.vegeMedDone && categoryLevel.equalsIgnoreCase("vegetablesmedium")){
                points = points + currentPoints;
                GameDifficulty.vegeMedDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else if(!GameDifficulty.vegeHardDone && categoryLevel.equalsIgnoreCase("vegetableshard")){
                points = points + currentPoints;
                GameDifficulty.vegeHardDone = true;
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }else {
                GameDifficulty.points = points;
                editor1.putInt("points", points);
                editor1.apply();
            }
        }
        SharedPreferences.Editor editor = getSharedPreferences("IMDONE", MODE_PRIVATE).edit();
        if (categoryLevel.equalsIgnoreCase("alphabetseasy")){
            editor.putBoolean("doneAE", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("alphabetsmedium")){
            editor.putBoolean("doneAM", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("alphabetshard")){
            editor.putBoolean("doneAH", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("numberseasy")){
            editor.putBoolean("doneNE", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("numbersmedium")){
            editor.putBoolean("doneNM", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("numbershard")){
            editor.putBoolean("doneNH", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("shapeseasy")){
            editor.putBoolean("doneSE", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("shapesmedium")){
            editor.putBoolean("doneSM", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("shapeshard")){
            editor.putBoolean("doneSH", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("colorseasy")){
            editor.putBoolean("doneCE", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("colorsmedium")){
            editor.putBoolean("doneCM", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("colorshard")){
            editor.putBoolean("doneCH", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("fruitseasy")){
            editor.putBoolean("doneFE", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("fruitsmedium")){
            editor.putBoolean("doneFM", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("fruitshard")){
            editor.putBoolean("doneFH", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("vegetableseasy")){
            editor.putBoolean("doneVE", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("vegetablesmedium")){
            editor.putBoolean("doneVM", true);
            editor.apply();
        }else if (categoryLevel.equalsIgnoreCase("vegetableshard")){
            editor.putBoolean("doneVH", true);
            editor.apply();
        }

        winpop.setVisibility(View.VISIBLE);
        winSfx.start();

    }

    private void update(int progress){
        int seconds = progress % 60;
        String secondsFinal = "";
        secondsFinal = String.valueOf(seconds);
        time.setText(secondsFinal);
        
        if (secondsFinal == String.valueOf(0)){
            time.setText(difficulty);
        }
    }

    @Override
    public void onBackPressed() {
        GameDifficulty.scorePoint.setText(String.valueOf(points));
        cardClosed.stop();
        toast.cancel();
        countDownTimer.cancel();
        super.onBackPressed();
        Animatoo.animateSlideUp(MemoryGame.this);


    }

    public void Back(View view) {
        mp = MediaPlayer.create(MemoryGame.this,R.raw.btn_klik);
        mp.start();
        GameDifficulty.scorePoint.setText(String.valueOf(points));
        cardClosed.stop();
        toast.cancel();
        countDownTimer.cancel();
        super.onBackPressed();
        Animatoo.animateSlideUp(MemoryGame.this);
    }

    public void Unlock(View view) {
        GameDifficulty.scorePoint.setText(String.valueOf(points));
        super.onBackPressed();
    }
}