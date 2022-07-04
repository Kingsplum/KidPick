package com.cite.kidpick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

public class GameDifficulty extends AppCompatActivity {
    List<Integer> images;
    List<Integer> imageToPass;
    MediaPlayer unlock,buttonClick;
   public static boolean isAlreadyDone = false;
    public static TextView scorePoint;
    ImageView starInfo;


    Button easy,medium,hard,lockMed,lockHard;
    Animation scaleUp,shake;
    Animation scaleDown;
    MediaPlayer btn_click;
    String category;

    ProgressBar progressBar;
     boolean colorMedUnlocked;
    boolean colorHardUnlocked;
    public static boolean colorEasyDone;
    public static boolean colorMedDone;
    public static boolean colorHardDone;


    boolean alphaMedUnlocked;
    boolean alphaHardUnlocked;
    public static boolean alphaEasyDone;
    public static boolean alphaMedDone;
    public static boolean alphaHardDone;

    boolean numMedUnlocked;
    boolean numHardUnlocked;
    public static boolean numEasyDone;
    public static boolean numMedDone;
    public static boolean numhaHardDone;


    boolean shapeMedUnlocked;
    boolean shapeHardUnlocked;
    public static boolean shapeEasyDone;
    public static boolean shapeMedDone;
    public static boolean shapeHardDone;

    boolean fruitMedUnlocked;
    boolean fruitHardUnlocked;
    public static boolean fruitEasyDone;
    public static boolean fruitMedDone;
    public static boolean fruitHardDone;

    boolean vegeMedUnlocked;
    boolean vegeHardUnlocked;
    public static boolean vegeEasyDone;
    public static boolean vegeMedDone;
    public static boolean vegeHardDone;

    public static int points;
    int priceMed,priceHard;
    int newPoints;

    RelativeLayout scoreContainer;
    ImageView cloud_top,cloud_mid,cloud_bot;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_difficulty);

        cloud_top = findViewById(R.id.cloud_top);
        cloud_mid = findViewById(R.id.cloud_mid);
        cloud_bot = findViewById(R.id.cloud_bot);

        TranslateAnimation cloud_movingT = new TranslateAnimation(
                Animation.ABSOLUTE, 1000,
                Animation.ABSOLUTE, -1000,
                Animation.ABSOLUTE, 200,
                Animation.ABSOLUTE, 200
        );
        TranslateAnimation cloud_movingM = new TranslateAnimation(
                Animation.ABSOLUTE, -400,
                Animation.ABSOLUTE, 1000,
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
        cloud_movingM.setStartOffset(-400);
        cloud_movingM.setRepeatCount(Animation.INFINITE);
        cloud_movingM.setRepeatMode(Animation.REVERSE);
        cloud_mid.startAnimation(cloud_movingM);

        cloud_movingB.setDuration(4000);
        cloud_movingB.setFillAfter(true);
        cloud_movingB.setStartOffset(1000);
        cloud_movingB.setRepeatCount(Animation.INFINITE);
        cloud_movingB.setRepeatMode(Animation.REVERSE);
        cloud_bot.startAnimation(cloud_movingB);

        unlock = MediaPlayer.create(this,R.raw.wingame_sfx);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         colorMedUnlocked = prefs.getBoolean("COLORMED",false);
         colorHardUnlocked = prefs.getBoolean("COLORHARD",false);
        alphaMedUnlocked = prefs.getBoolean("ALPHAMED",false);
        alphaHardUnlocked = prefs.getBoolean("ALPHAHARD",false);

        numMedUnlocked = prefs.getBoolean("NUMMED",false);
        numHardUnlocked = prefs.getBoolean("NUMHARD",false);
        shapeMedUnlocked = prefs.getBoolean("SHAPEMED",false);
        shapeHardUnlocked = prefs.getBoolean("SHAPEHARD",false);

        fruitMedUnlocked = prefs.getBoolean("FRUITMED",false);
        fruitHardUnlocked = prefs.getBoolean("FRUITHARD",false);
        vegeMedUnlocked = prefs.getBoolean("VEGEMED",false);
        vegeHardUnlocked = prefs.getBoolean("VEGEHARD",false);

        SharedPreferences levelDone = getSharedPreferences("IMDONE", MODE_PRIVATE);
        alphaEasyDone = levelDone.getBoolean("doneAE",false);
        alphaMedDone = levelDone.getBoolean("doneAM",false);
        alphaHardDone = levelDone.getBoolean("doneAH",false);

        shapeEasyDone = levelDone.getBoolean("doneSE",false);
        shapeMedDone = levelDone.getBoolean("doneSM",false);
        shapeHardDone = levelDone.getBoolean("doneSH",false);

        numEasyDone = levelDone.getBoolean("doneNE",false);
        numMedDone = levelDone.getBoolean("doneNM",false);
        numhaHardDone = levelDone.getBoolean("doneNH",false);

        colorEasyDone = levelDone.getBoolean("doneCE",false);
        colorMedDone = levelDone.getBoolean("doneCM",false);
        colorHardDone = levelDone.getBoolean("doneCH",false);

        fruitEasyDone = levelDone.getBoolean("doneFE",false);
        fruitMedDone = levelDone.getBoolean("doneFM",false);
        fruitHardDone = levelDone.getBoolean("doneFH",false);

        vegeEasyDone = levelDone.getBoolean("doneVE",false);
        vegeMedDone = levelDone.getBoolean("doneVM",false);
        vegeHardDone = levelDone.getBoolean("doneVH",false);


        SharedPreferences prefsUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE);
         points = prefsUnlock.getInt("points", 0);
         
       



        images = (ArrayList<Integer>)getIntent().getSerializableExtra("IMAGES");
        imageToPass = new ArrayList<>();
        category = getIntent().getStringExtra("CATEGORY");

        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down);
        shake = AnimationUtils.loadAnimation(this,R.anim.rotate);

        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        lockMed = findViewById(R.id.lockeMedium);
        lockHard = findViewById(R.id.lockeHard);
        scoreContainer = findViewById(R.id.score_container);
        starInfo = findViewById(R.id.starImg);

        scorePoint = findViewById(R.id.point_score);
        scorePoint.setText(String.valueOf(points));
















        btn_click = MediaPlayer.create(this,R.raw.btn_klik);



        progressBar = findViewById(R.id.progress_circular);


        if (category.equalsIgnoreCase("Colors")){
            priceMed = 8;
            priceHard = 11;
            lockMed.setText("Unlock for "+priceMed+" stars");
            lockHard.setText("Unlock for "+priceHard+" stars");
            if (colorEasyDone){
                easy.setTextColor(Color.parseColor("#77ff03"));
            }
            if (colorMedDone){
                medium.setTextColor(Color.parseColor("#77ff03"));
            }
            if (colorHardDone){
                hard.setTextColor(Color.parseColor("#77ff03"));
            }


            if (colorMedUnlocked){
                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
            }
            if (colorHardUnlocked){
                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
            }



        }else if (category.equalsIgnoreCase("alphabets")){
            priceHard = 5;
            priceMed = 2;
            lockMed.setText("Unlock for "+priceMed+" stars");
            lockHard.setText("Unlock for "+priceHard+" stars");
            if (alphaEasyDone){
                easy.setTextColor(Color.parseColor("#77ff03"));
            }
            if (alphaMedDone){
                medium.setTextColor(Color.parseColor("#77ff03"));
            }
            if (alphaHardDone){
                hard.setTextColor(Color.parseColor("#77ff03"));
            }

            if (alphaMedUnlocked){
                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
            }
            if (alphaHardUnlocked){
                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
            }
        }else if (category.equalsIgnoreCase("numbers")){
            priceHard = 8;
            priceMed = 3;
            lockMed.setText("Unlock for "+priceMed+" stars");
            lockHard.setText("Unlock for "+priceHard+" stars");
            if (numEasyDone){
                easy.setTextColor(Color.parseColor("#77ff03"));
            }
            if (numMedDone){
                medium.setTextColor(Color.parseColor("#77ff03"));
            }
            if (numhaHardDone){
                hard.setTextColor(Color.parseColor("#77ff03"));
            }

            if (numMedUnlocked){
                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
                
            }
            if (numHardUnlocked){
                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);

            }
        }else if (category.equalsIgnoreCase("fruits")){
            priceHard = 13;
            priceMed = 10;
            lockMed.setText("Unlock for "+priceMed+" stars");
            lockHard.setText("Unlock for "+priceHard+" stars");
            if (fruitEasyDone){
                easy.setTextColor(Color.parseColor("#77ff03"));
            }
            if (fruitMedDone){
                medium.setTextColor(Color.parseColor("#77ff03"));
            }
            if (fruitHardDone){
                hard.setTextColor(Color.parseColor("#77ff03"));
            }

            if (fruitMedUnlocked){
                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);

            }
            if (fruitHardUnlocked){
                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);

            }
        }else if (category.equalsIgnoreCase("vegetables")){
            priceMed = 12;
            priceHard = 15;
            lockMed.setText("Unlock for "+priceMed+" stars");
            lockHard.setText("Unlock for "+priceHard+" stars");
            if (vegeEasyDone){
                easy.setTextColor(Color.parseColor("#77ff03"));
            }
            if (vegeMedDone){
                medium.setTextColor(Color.parseColor("#77ff03"));
            }
            if (vegeHardDone){
                hard.setTextColor(Color.parseColor("#77ff03"));
            }


            if (vegeMedUnlocked){
                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);

            }
            if (vegeHardUnlocked){
                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
            }
        }else if (category.equalsIgnoreCase("shapes")){
            priceMed = 6;
            priceHard = 10;
            lockMed.setText("Unlock for "+priceMed+" stars");
            lockHard.setText("Unlock for "+priceHard+" stars");
            if (shapeEasyDone){
                easy.setTextColor(Color.parseColor("#77ff03"));
            }
            if (shapeMedDone){
                medium.setTextColor(Color.parseColor("#77ff03"));
            }
            if (shapeHardDone){
                hard.setTextColor(Color.parseColor("#77ff03"));
            }

            if (shapeMedUnlocked){
                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);

            }
            if (shapeHardUnlocked){
                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
            }
        }

       starInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               starInfo.startAnimation(scaleDown);
               TooltipCompat.setTooltipText(starInfo, "Repeated Win is not counted");
           }
       });



        easy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    easy.startAnimation(scaleUp);
                    return true;
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    easy.setClickable(false);
                    btn_click.start();
                    medium.setClickable(false);
                    hard.setClickable(false);
                    easy.startAnimation(scaleDown);
                    loadCardsEasy();

                    return true;
                }

                return false;

            }
        });

        lockMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnlockMedium(prefs,prefsUnlock,shake);
            }
        });
        lockHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnlockHard(prefs,prefsUnlock,shake);
            }
        });

        medium.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    medium.startAnimation(scaleUp);
                    return true;
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btn_click.start();
                    easy.setClickable(false);
                    hard.setClickable(false);
                    medium.startAnimation(scaleDown);

                 //   Toast.makeText(GameDifficulty.this, String.valueOf(points), Toast.LENGTH_SHORT).show();


                  loadCardsMedium();

                    return true;
                }

                return false;

            }
        });

        hard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    hard.startAnimation(scaleUp);
                    return true;
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    btn_click.start();
                    medium.setClickable(false);
                    easy.setClickable(false);
                    hard.startAnimation(scaleDown);
                    loadCardsHard();
                   /* Intent intent = new Intent(GameCategory.this,GameDifficulty.class);
                    intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                    startActivity(intent);*/

                    return true;
                }

                return false;

            }
        });


    }

    private void UnlockHard(SharedPreferences prefs, SharedPreferences prefsUnlock, Animation shake) {

        if (category.equalsIgnoreCase("colors")){
            if (points >= priceHard){
                points = points - priceHard;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("COLORHARD", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
                unlock.start();
                hard.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }

        }else if (category.equalsIgnoreCase("alphabets")){
            if (points >= priceHard){
                points = points - priceHard;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("ALPHAHARD", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
                unlock.start();
                hard.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("numbers")){
            if (points >= priceHard){
                points = points - priceHard;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("NUMHARD", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
                unlock.start();
                hard.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("shapes")){
            if (points >= priceHard){
                points = points - priceHard;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("SHAPEHARD", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
                unlock.start();
                hard.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("fruits")){
            if (points >= priceHard){
                points = points - priceHard;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("FRUITHARD", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
                unlock.start();
                hard.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("vegetables")){
            if (points >= priceHard){
                points = points - priceHard;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("VEGEHARD", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockHard.setVisibility(View.GONE);
                hard.setVisibility(View.VISIBLE);
                unlock.start();
                hard.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void UnlockMedium(SharedPreferences prefs, SharedPreferences prefsUnlock, Animation shake) {
        if (category.equalsIgnoreCase("colors")){
            if (points >= priceMed){
                points = points - priceMed;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("COLORMED", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
                unlock.start();
                medium.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }

        }else if (category.equalsIgnoreCase("alphabets")){
            if (points >= priceMed){
                points = points - priceMed;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("ALPHAMED", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
                unlock.start();
                medium.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("numbers")){
            if (points >= priceMed){
                points = points - priceMed;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("NUMMED", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
                unlock.start();
                medium.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("shapes")){
            if (points >= priceMed){
                points = points - priceMed;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("SHAPEMED", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
                unlock.start();
                medium.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("fruits")){
            if (points >= priceMed){
                points = points - priceMed;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("FRUITMED", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
                unlock.start();
                medium.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }else if (category.equalsIgnoreCase("vegetables")){
            if (points >= priceMed){
                points = points - priceMed;
                scorePoint.setText(String.valueOf(points));
                SharedPreferences.Editor editor = prefs.edit();
                SharedPreferences.Editor editorUnlock = prefsUnlock.edit();
                editor.putBoolean("VEGEMED", true);
                editor.apply();

                editorUnlock = getSharedPreferences("UNLOCK", MODE_PRIVATE).edit();
                editorUnlock.putInt("points", points);
                editorUnlock.apply();

                lockMed.setVisibility(View.GONE);
                medium.setVisibility(View.VISIBLE);
                unlock.start();
                medium.startAnimation(scaleUp);
            }else {
                scoreContainer.startAnimation(this.shake);
                Toast.makeText(GameDifficulty.this, "Not Enough Stars.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void loadCardsEasy() {
        progressBar.setVisibility(View.VISIBLE);
        imageToPass.clear();
        for (int i = 0; i < images.size()*0.3; i++){
            imageToPass.add(images.get(i));
        }
        imageToPass.addAll(imageToPass);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);

                Intent intent = new Intent(GameDifficulty.this,MemoryGame.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putExtra("DIFFICULTY","Easy");
                intent.putExtra("CATEGORYLEVEL",category+"Easy");
                intent.putExtra("SAVE_POINTS",points);
                medium.setEnabled(true);
                hard.setEnabled(true);
                easy.setClickable(true);
                startActivity(intent);
                Animatoo.animateSlideDown(GameDifficulty.this);


            }
        },2000);


    }



    private void loadCardsMedium() {
        progressBar.setVisibility(View.VISIBLE);
        imageToPass.clear();
        for (int i = 0; i < images.size()*0.6; i++){
            imageToPass.add(images.get(i));
        }

        imageToPass.addAll(imageToPass);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(GameDifficulty.this,MemoryGame.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putExtra("DIFFICULTY","Medium");
                intent.putExtra("CATEGORYLEVEL",category+"Medium");
                intent.putExtra("SAVE_POINTS",points);
                startActivity(intent);
                easy.setEnabled(true);
                hard.setEnabled(true);

            }
        },2000);
    }

    private void loadCardsHard() {
        progressBar.setVisibility(View.VISIBLE);
        imageToPass.clear();
        for (int i = 0; i < images.size(); i++){
            imageToPass.add(images.get(i));
        }
        imageToPass.addAll(imageToPass);
      
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(GameDifficulty.this,MemoryGame.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putExtra("DIFFICULTY","Hard");
                intent.putExtra("CATEGORYLEVEL",category+"Hard");
                intent.putExtra("SAVE_POINTS",points);
                startActivity(intent);
                medium.setEnabled(true);
                easy.setEnabled(true);

            }
        },2000);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameDifficulty.this,GameCategory.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(GameDifficulty.this);
        finishAffinity();
    }

    public void Back(View view) {
        buttonClick = MediaPlayer.create(this,R.raw.btn_klik);
        buttonClick.start();
        Intent intent = new Intent(GameDifficulty.this,GameCategory.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(GameDifficulty.this);
        finishAffinity();
    }

    public void Home(View view) {
        buttonClick = MediaPlayer.create(this,R.raw.btn_klik);
        buttonClick.start();
        Intent intent = new Intent(GameDifficulty.this,HomeScreen.class);
        startActivity(intent);
        Animatoo.animateSlideUp(GameDifficulty.this);
        finishAffinity();
    }

}