package com.cite.kidpick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class FlashCards extends AppCompatActivity {
    ImageButton nextbtn,backbtn,skipbtn,speakBtn;
    TextView actTitle;
    Button finishBtn;
    Animation shake;
    KonfettiView celebration;
    MediaPlayer mp,confetti,mp1;

    ArrayList<MyModel> modelArrayList;
    private ViewPager viewPager;
    private MyAdapter myAdapter;

    List<Integer> images;
    List<Integer> audio;
    List<String> titles;
    String category;

    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
                mediaPlayer.release();
                mp = null;

            }
        };


        images = (ArrayList<Integer>)getIntent().getSerializableExtra("IMAGES");
        audio = (ArrayList<Integer>)getIntent().getSerializableExtra("AUDIO");
        titles = (ArrayList<String>)getIntent().getSerializableExtra("TITLES");
        category = getIntent().getStringExtra("CAT");

        modelArrayList = new ArrayList<>();
        mp = MediaPlayer.create(FlashCards.this, audio.get(0));
        mp.start();
        mp.setOnCompletionListener(onCompletionListener);

        //colors
        Integer[] temp_color = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color9),
                getResources().getColor(R.color.color10),
                getResources().getColor(R.color.color11),
                getResources().getColor(R.color.color12),
                getResources().getColor(R.color.color13),
                getResources().getColor(R.color.color14),
                getResources().getColor(R.color.color15),
                getResources().getColor(R.color.color16),
                getResources().getColor(R.color.color17),
                getResources().getColor(R.color.color18),
                getResources().getColor(R.color.color19),
                getResources().getColor(R.color.color20),
                getResources().getColor(R.color.color21),
                getResources().getColor(R.color.color22),
                getResources().getColor(R.color.color23),
                getResources().getColor(R.color.color24),
                getResources().getColor(R.color.color25),
                getResources().getColor(R.color.color26)

        };

        if (category.equalsIgnoreCase("colors")){
            temp_color = new Integer[]{
                    getResources().getColor(R.color.black),
                    getResources().getColor(R.color.white),
                    getResources().getColor(R.color.color18),
                    getResources().getColor(R.color.color9),
                    getResources().getColor(R.color.color6),
                    getResources().getColor(R.color.color22),
                    getResources().getColor(R.color.color23),
                    getResources().getColor(R.color.gray),
                    getResources().getColor(R.color.color12),
                    getResources().getColor(R.color.color2),
                    getResources().getColor(R.color.color4)
            };

        }

        celebration = findViewById(R.id.celebrationView);



        //Buttons
        backbtn = findViewById(R.id.backbtn);
        nextbtn = findViewById(R.id.nextbtn);
        skipbtn = findViewById(R.id.skipbtn);
        speakBtn = findViewById(R.id.speakerbtn);
        finishBtn = findViewById(R.id.finishbtn);
        viewPager = findViewById(R.id.viewPager);
        //title app
        actTitle = findViewById(R.id.activity_title);
        shake = AnimationUtils.loadAnimation(this,R.anim.rotate);

        //set text to act title
        actTitle.setText(category);
        //set invisible for finish button
        colors = temp_color;
        backbtn.setVisibility(View.INVISIBLE);
        finishBtn.setVisibility(View.INVISIBLE);
       // loadCards();

        for (int i = 0; i < images.size(); i++){
            modelArrayList.add(new MyModel(titles.get(i),images.get(i)));
        }

        //setup adapter
        myAdapter = new MyAdapter(this,modelArrayList);
        //set adapter to viewpager
        viewPager.setAdapter(myAdapter);
        //set dafault padding from left/right
        viewPager.setPadding(0,150,0,150);





        backbtn.setOnClickListener(v -> {

           /* mp1 = MediaPlayer.create(FlashCards.this, R.raw.btn_klik);
            mp1.start();
*/
            if (getItem(0) > 0){

                //MyAdapter.banner.startAnimation(shake);

                viewPager.setCurrentItem(getItem(-1),true);


            }

        });


        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(FlashCards.this, audio.get(getItem(0)));
                mp.start();
                mp.setOnCompletionListener(onCompletionListener);
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   mp1 = MediaPlayer.create(Alphabets.this, R.raw.btn_klik);
                mp1.start();
*/
                if (getItem(0) < images.size()-1){

                    viewPager.setCurrentItem(getItem(1),true);

                }


            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp1 = MediaPlayer.create(FlashCards.this, R.raw.btn_klik);
                mp1.start();


                Intent i = new Intent(FlashCards.this,HomeScreen.class);
                startActivity(i);
                finishAffinity();


            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   mp1 = MediaPlayer.create(FlashCards.this, R.raw.btn_klik);
                mp1.start();
*/
                finishBtn.setEnabled(false);
                finishBtn.setClickable(false);
                backbtn.setClickable(false);
                backbtn.setEnabled(false);
                confetti = MediaPlayer.create(FlashCards.this,R.raw.confetti);
                confetti.start();

                celebration.build()
                        .addColors(Color.RED,Color.YELLOW,Color.MAGENTA,Color.WHITE,Color.GREEN)
                        .setDirection(0.0,359)
                        .setSpeed(1f,5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.RECT,Shape.CIRCLE)
                        .addSizes(new Size(12,5))
                        .setPosition(-50f,celebration.getWidth()+50f,-50f,-50f)
                        .streamFor(300,3000L);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(FlashCards.this,FlashCardCategory.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                },5000);
            }
        });
//
//        Toast.makeText(this, String.valueOf(images.size()), .LENGTH_SHORT).show();

        //set Viewpager change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                shake = AnimationUtils.loadAnimation(FlashCards.this,R.anim.rotate);
                if( position < (myAdapter.getCount()-1) && position<(colors.length-1)){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1])
                    );
                }else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }

            }

            @Override
            public void onPageSelected(int position) {

                //audio
                mp = MediaPlayer.create(FlashCards.this, audio.get(position));
                mp.start();
                mp.setOnCompletionListener(onCompletionListener);



                if (position > 0){
                    backbtn.setVisibility(View.VISIBLE);
                }else {
                    backbtn.setVisibility(View.INVISIBLE);
                }
                if (position < images.size()-1){
                    nextbtn.setVisibility(View.VISIBLE);
                    finishBtn.setVisibility(View.INVISIBLE);
                }else {
                    nextbtn.setVisibility(View.INVISIBLE);
                    finishBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int getItem(int i) {
            return viewPager.getCurrentItem() + i;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FlashCards.this,FlashCardCategory.class);
        startActivity(intent);
        Animatoo.animateShrink(this);
        finishAffinity();
    }
}