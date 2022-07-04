package com.cite.kidpick;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlashCardCategory extends AppCompatActivity {
    Animation scaleUp;
    Animation scaleDown;
    RelativeLayout alphabets,numbers,shapes,colors,fruits,vegetables;

    private  List<Integer> imageToPass;
    private  List<Integer> audioToPass;
    private  List<String> titleToPass;
    Bundle args = new Bundle();
    String category;
    MediaPlayer click;
    ImageView cloud_top,cloud_mid,cloud_bot;


    @SuppressLint({"ClickableViewAccessibility", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_category);

        DisplayMetrics displayMetrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
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

        //init list
        imageToPass = new ArrayList<>();
        titleToPass = new ArrayList<>();
        audioToPass = new ArrayList<>();

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
                Intent intent = new Intent(FlashCardCategory.this,FlashCards.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putStringArrayListExtra("TITLES",(ArrayList<String>) titleToPass);
                intent.putIntegerArrayListExtra("AUDIO",(ArrayList<Integer>) audioToPass);
                intent.putExtra("CAT",category);
                startActivity(intent);
                Animatoo.animateZoom(this);
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
                Intent intent = new Intent(FlashCardCategory.this,FlashCards.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putStringArrayListExtra("TITLES",(ArrayList<String>) titleToPass);
                intent.putIntegerArrayListExtra("AUDIO",(ArrayList<Integer>) audioToPass);
                intent.putExtra("CAT",category);
                startActivity(intent);
                Animatoo.animateZoom(this);
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
                Intent intent = new Intent(FlashCardCategory.this,FlashCards.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putStringArrayListExtra("TITLES",(ArrayList<String>) titleToPass);
                intent.putIntegerArrayListExtra("AUDIO",(ArrayList<Integer>) audioToPass);
                intent.putExtra("CAT",category);
                startActivity(intent);
                Animatoo.animateZoom(this);
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
                Intent intent = new Intent(FlashCardCategory.this,FlashCards.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putStringArrayListExtra("TITLES",(ArrayList<String>) titleToPass);
                intent.putIntegerArrayListExtra("AUDIO",(ArrayList<Integer>) audioToPass);
                intent.putExtra("CAT",category);
                startActivity(intent);
                Animatoo.animateZoom(this);
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
                Intent intent = new Intent(FlashCardCategory.this,FlashCards.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putStringArrayListExtra("TITLES",(ArrayList<String>) titleToPass);
                intent.putIntegerArrayListExtra("AUDIO",(ArrayList<Integer>) audioToPass);
                intent.putExtra("CAT",category);
                startActivity(intent);
                Animatoo.animateZoom(this);
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
                Intent intent = new Intent(FlashCardCategory.this,FlashCards.class);
                intent.putIntegerArrayListExtra("IMAGES", (ArrayList<Integer>) imageToPass);
                intent.putStringArrayListExtra("TITLES",(ArrayList<String>) titleToPass);
                intent.putIntegerArrayListExtra("AUDIO",(ArrayList<Integer>) audioToPass);
                intent.putExtra("CAT",category);
                startActivity(intent);
                Animatoo.animateZoom(this);
            }
            return true;
        });
    }

    private void loadCardsVegetables(){
        imageToPass.add(R.drawable.bottle_gourd);
        imageToPass.add(R.drawable.brocoli);
        imageToPass.add(R.drawable.cabbage);
        imageToPass.add(R.drawable.carrots);
        imageToPass.add(R.drawable.cocumber);
        imageToPass.add(R.drawable.corn);
        imageToPass.add(R.drawable.eggplant);
        imageToPass.add(R.drawable.garlic);
        imageToPass.add(R.drawable.ginger);
        imageToPass.add(R.drawable.jicama);
        imageToPass.add(R.drawable.onion);
        imageToPass.add(R.drawable.pepper);
        imageToPass.add(R.drawable.potato);
        imageToPass.add(R.drawable.radish);

        titleToPass.add("Bottle Gourd");
        titleToPass.add("Broccoli");
        titleToPass.add("Cabbage");
        titleToPass.add("Carrots");
        titleToPass.add("Cucumber");
        titleToPass.add("Corn");
        titleToPass.add("Eggplant");
        titleToPass.add("Garlic");
        titleToPass.add("Ginger");
        titleToPass.add("Jicama");
        titleToPass.add("Onion");
        titleToPass.add("Pepper");
        titleToPass.add("Potato");
        titleToPass.add("Radish");

        audioToPass.add(R.raw.veg_bottle_gourd);
        audioToPass.add(R.raw.veg_broccoli);
        audioToPass.add(R.raw.veg_cabbage);
        audioToPass.add(R.raw.veg_carrots);
        audioToPass.add(R.raw.veg_cucumber);
        audioToPass.add(R.raw.veg_corn);
        audioToPass.add(R.raw.veg_eggplant);
        audioToPass.add(R.raw.veg_garlic);
        audioToPass.add(R.raw.veg_ginger);
        audioToPass.add(R.raw.veg_jicama);
        audioToPass.add(R.raw.veg_onion);
        audioToPass.add(R.raw.veg_pepper);
        audioToPass.add(R.raw.veg_potato);
        audioToPass.add(R.raw.veg_radish);

        category = "Vegetables";

    }


    private void loadCardsFruits(){
        imageToPass.add(R.drawable.apple);
        imageToPass.add(R.drawable.avocado);
        imageToPass.add(R.drawable.banana);
        imageToPass.add(R.drawable.grapes);
        imageToPass.add(R.drawable.guava);
        imageToPass.add(R.drawable.kiwi);
        imageToPass.add(R.drawable.lemon);
        imageToPass.add(R.drawable.orange);
        imageToPass.add(R.drawable.pineapple);
        imageToPass.add(R.drawable.strawberry);
        imageToPass.add(R.drawable.tomato);
        imageToPass.add(R.drawable.watermelon);

        titleToPass.add("Apple");
        titleToPass.add("Avocado");
        titleToPass.add("Banana");
        titleToPass.add("Grapes");
        titleToPass.add("Guava");
        titleToPass.add("Kiwi");
        titleToPass.add("Lemon");
        titleToPass.add("Orange");
        titleToPass.add("Pineapple");
        titleToPass.add("Strawberry");
        titleToPass.add("Tomato");
        titleToPass.add("Watermelon");

        audioToPass.add(R.raw.fruit_apple);
        audioToPass.add(R.raw.fruit_avocado);
        audioToPass.add(R.raw.fruit_banana);
        audioToPass.add(R.raw.fruit_grapes);
        audioToPass.add(R.raw.fruit_guava);
        audioToPass.add(R.raw.fruit_kiwi);
        audioToPass.add(R.raw.fruit_lemon);
        audioToPass.add(R.raw.fruit_orange);
        audioToPass.add(R.raw.fruit_pineapple);
        audioToPass.add(R.raw.fruit_strawberry);
        audioToPass.add(R.raw.fruit_tomato);
        audioToPass.add(R.raw.fruit_watermelon);

        category = "Fruits";
    }

    private void loadCardsNumbers() {
        imageToPass.add(R.drawable.zero);
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
        imageToPass.add(R.drawable.eleven);
        imageToPass.add(R.drawable.twelve);
        imageToPass.add(R.drawable.thirteen);
        imageToPass.add(R.drawable.fourteen);
        imageToPass.add(R.drawable.fifteen);
        imageToPass.add(R.drawable.sixteen);
        imageToPass.add(R.drawable.seventeen);
        imageToPass.add( R.drawable.eighteen);
        imageToPass.add(R.drawable.nineteen);
        imageToPass.add(R.drawable.twenty);

        titleToPass.add("Zero");
        titleToPass.add("One");
        titleToPass.add("Two");
        titleToPass.add("Three");
        titleToPass.add("Four");
        titleToPass.add("Five");
        titleToPass.add("Six");
        titleToPass.add("Seven");
        titleToPass.add("Eight");
        titleToPass.add("Nine");
        titleToPass.add("Ten");
        titleToPass.add("Eleven");
        titleToPass.add("Twelve");
        titleToPass.add("Thirteen");
        titleToPass.add("Fourteen");
        titleToPass.add("Fifteen");
        titleToPass.add("Sixteen");
        titleToPass.add("Seventeen");
        titleToPass.add("Eighteen");
        titleToPass.add("Nineteen");
        titleToPass.add("Twenty");

        audioToPass.add(R.raw.n0);
        audioToPass.add(R.raw.n1);
        audioToPass.add(R.raw.n2);
        audioToPass.add(R.raw.n3);
        audioToPass.add(R.raw.n4);
        audioToPass.add(R.raw.n5);
        audioToPass.add(R.raw.n6);
        audioToPass.add(R.raw.n7);
        audioToPass.add(R.raw.n8);
        audioToPass.add(R.raw.n9);
        audioToPass.add(R.raw.n10);
        audioToPass.add(R.raw.n11);
        audioToPass.add(R.raw.n12);
        audioToPass.add(R.raw.n13);
        audioToPass.add(R.raw.n14);
        audioToPass.add(R.raw.n15);
        audioToPass.add(R.raw.n16);
        audioToPass.add(R.raw.n17);
        audioToPass.add(R.raw.n18);
        audioToPass.add(R.raw.n19);
        audioToPass.add(R.raw.n20);

        category = "Numbers";

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
        imageToPass.add(R.drawable.ic_k);
        imageToPass.add(R.drawable.ic_l);
        imageToPass.add(R.drawable.ic_m);
        imageToPass.add(R.drawable.ic_n);
        imageToPass.add(R.drawable.ic_o);
        imageToPass.add(R.drawable.ic_p);
        imageToPass.add(R.drawable.ic_q);
        imageToPass.add(R.drawable.ic_r);
        imageToPass.add( R.drawable.ic_s);
        imageToPass.add(R.drawable.ic_t);
        imageToPass.add(R.drawable.ic_u);
        imageToPass.add(R.drawable.ic_v);
        imageToPass.add(R.drawable.ic_w);
        imageToPass.add(R.drawable.ic_x);
        imageToPass.add(R.drawable.ic_y);
        imageToPass.add(R.drawable.ic_z);

        audioToPass.add( R.raw.a);
        audioToPass.add(R.raw.b);
        audioToPass.add(R.raw.c);
        audioToPass.add(R.raw.d);
        audioToPass.add(R.raw.e);
        audioToPass.add(R.raw.f);
        audioToPass.add(R.raw.g);
        audioToPass.add(R.raw.h);
        audioToPass.add(R.raw.i);
        audioToPass.add(R.raw.j);
        audioToPass.add(R.raw.k);
        audioToPass.add(R.raw.l);
        audioToPass.add(R.raw.m);
        audioToPass.add(R.raw.n);
        audioToPass.add(R.raw.o);
        audioToPass.add(R.raw.p);
        audioToPass.add(R.raw.q);
        audioToPass.add(R.raw.r);
        audioToPass.add(R.raw.s);
        audioToPass.add(R.raw.t);
        audioToPass.add(R.raw.u);
        audioToPass.add(R.raw.v);
        audioToPass.add(R.raw.w);
        audioToPass.add(R.raw.x);
        audioToPass.add(R.raw.y);
        audioToPass.add(R.raw.z);



        titleToPass.add("A-a");
        titleToPass.add("B-b");
        titleToPass.add("C-c");
        titleToPass.add("D-d");
        titleToPass.add("E-e");
        titleToPass.add("F-f");
        titleToPass.add("G-g");
        titleToPass.add("H-h");
        titleToPass.add("I-i");
        titleToPass.add("J-j");
        titleToPass.add("K-k");
        titleToPass.add("L-l");
        titleToPass.add("M-m");
        titleToPass.add("N-n");
        titleToPass.add("O-o");
        titleToPass.add("P-p");
        titleToPass.add("Q-q");
        titleToPass.add("R-r");
        titleToPass.add("S-s");
        titleToPass.add("T-t");
        titleToPass.add("U-u");
        titleToPass.add("V-v");
        titleToPass.add("W-w");
        titleToPass.add("X-x");
        titleToPass.add("Y-y");
        titleToPass.add("Z-z");

        category = "Alphabets";


    }

    private void loadCardsColors(){
        imageToPass.add(R.drawable.ic_black);
        imageToPass.add(R.drawable.ic_white);
        imageToPass.add(R.drawable.ic_red);
        imageToPass.add(R.drawable.ic_green);
        imageToPass.add(R.drawable.ic_yellow);
        imageToPass.add(R.drawable.ic_blue);
        imageToPass.add(R.drawable.ic_pink);
        imageToPass.add(R.drawable.ic_gray);
        imageToPass.add(R.drawable.ic_brown);
        imageToPass.add(R.drawable.ic_orange);
        imageToPass.add(R.drawable.ic_purple);

        titleToPass.add("Black");
        titleToPass.add("White");
        titleToPass.add("Red");
        titleToPass.add("Green");
        titleToPass.add("yellow");
        titleToPass.add("Blue");
        titleToPass.add("Pink");
        titleToPass.add("Gray");
        titleToPass.add("Brown");
        titleToPass.add("Orange");
        titleToPass.add("Purple");


        audioToPass.add(R.raw.black);
        audioToPass.add(R.raw.white);
        audioToPass.add(R.raw.red);
        audioToPass.add(R.raw.green);
        audioToPass.add(R.raw.yellow);
        audioToPass.add(R.raw.blue);
        audioToPass.add(R.raw.pink);
        audioToPass.add(R.raw.grey);
        audioToPass.add(R.raw.brown);
        audioToPass.add(R.raw.orange);
        audioToPass.add(R.raw.purple);

        category = "Colors";


    }

    private void loadCardsShapes(){
        imageToPass.add(R.drawable.parallelogram);
        imageToPass.add(R.drawable.rectangle);
        imageToPass.add(R.drawable.rhombus);
        imageToPass.add(R.drawable.star);
        imageToPass.add(R.drawable.trapezoid);
        imageToPass.add(R.drawable.arrow);
        imageToPass.add(R.drawable.circle);
        imageToPass.add(R.drawable.ellipse);
        imageToPass.add(R.drawable.heart);
        imageToPass.add(R.drawable.triangle);
        imageToPass.add(R.drawable.square);
        imageToPass.add(R.drawable.pentagon);
        imageToPass.add(R.drawable.hexagon);
        imageToPass.add(R.drawable.heptagon);
        imageToPass.add(R.drawable.octagon);
        imageToPass.add(R.drawable.nonagon);


        titleToPass.add("Parallelogram");
        titleToPass.add("Rectangle");
        titleToPass.add("Rhombus");
        titleToPass.add("Star");
        titleToPass.add("Trapezoid");
        titleToPass.add("Arrow");
        titleToPass.add("Circle");
        titleToPass.add("Ellipse");
        titleToPass.add("Heart");
        titleToPass.add("Triangle");
        titleToPass.add("Square");
        titleToPass.add("Pentagon");
        titleToPass.add("Hexagon");
        titleToPass.add("Heptagon");
        titleToPass.add("Octagon");
        titleToPass.add("Nonagon");

        audioToPass.add(R.raw.shape_parallelogram);
        audioToPass.add(R.raw.shape_rectangle);
        audioToPass.add(R.raw.shape_rhombus);
        audioToPass.add(R.raw.shape_star);
        audioToPass.add(R.raw.shape_trapezoid);
        audioToPass.add(R.raw.shape_arrow);
        audioToPass.add(R.raw.shape_circle);
        audioToPass.add(R.raw.shape_ellipse);
        audioToPass.add(R.raw.shape_heart);
        audioToPass.add(R.raw.shape_triangle);
        audioToPass.add(R.raw.shape_square);
        audioToPass.add(R.raw.shape_pentagon);
        audioToPass.add(R.raw.shape_hexagon);
        audioToPass.add(R.raw.shape_heptagon);
        audioToPass.add(R.raw.shape_octagon);
        audioToPass.add(R.raw.shape_nonagon);

        category = "Shapes";

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FlashCardCategory.this, HomeScreen.class);
        startActivity(intent);
        Animatoo.animateShrink(this);
        finishAffinity();
    }
}