package com.cite.kidpick;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;

public class HomeScreen extends AppCompatActivity {
    Button learn,play;
    Animation scaleUp;
    Animation scaleDown;
    Animation shake;
    private MediaPlayer mp,btn_sfx;
    ImageButton sounds;

    ImageView cloud,cloud2,cloud3,butt;
    AudioManager audioManager;
    int maxVolume,currentVol;
    SeekBar seekBar;
    boolean isMute = false;
    BackgroundSoundService backgroundSoundService;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        backgroundSoundService = new BackgroundSoundService();
      //  Intent svc=new Intent(this, BackgroundSoundService.class);
      //  startService(svc);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
         seekBar = findViewById(R.id.volume);
        seekFunction();



        cloud = findViewById(R.id.cloud);
        cloud2 = findViewById(R.id.cloud2);
        cloud3 = findViewById(R.id.cloud3);
        butt = findViewById(R.id.butt);

        mp= MediaPlayer.create(HomeScreen.this,R.raw.bg_music);
        btn_sfx= MediaPlayer.create(HomeScreen.this,R.raw.btn_klik);
       // mp.setLooping(true);
       // mp.start();

        TranslateAnimation cloud_moving = new TranslateAnimation(
                Animation.ABSOLUTE, 1000,
                Animation.ABSOLUTE, -1000,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0
        );
        TranslateAnimation cloud_moving3 = new TranslateAnimation(
                Animation.ABSOLUTE, 1000,
                Animation.ABSOLUTE, -1000,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0
        );

        TranslateAnimation cloud_moving2 = new TranslateAnimation(
                Animation.ABSOLUTE, -1000,
                Animation.ABSOLUTE, 1000,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0
        );

        cloud_moving.setDuration(8000);
        cloud_moving.setFillAfter(true);
        cloud_moving.setStartOffset(1000);
        cloud_moving.setRepeatCount(Animation.INFINITE);
        cloud_moving.setRepeatMode(Animation.REVERSE);

        cloud_moving2.setDuration(6000);
        cloud_moving2.setFillAfter(true);
        cloud_moving2.setStartOffset(1000);
        cloud_moving2.setRepeatCount(Animation.INFINITE);
        cloud_moving2.setRepeatMode(Animation.REVERSE);

        cloud_moving3.setDuration(10000);
        cloud_moving3.setFillAfter(true);
        cloud_moving3.setStartOffset(1000);
        cloud_moving3.setRepeatCount(Animation.INFINITE);
        cloud_moving3.setRepeatMode(Animation.REVERSE);

        cloud3.startAnimation(cloud_moving3);
        cloud2.startAnimation(cloud_moving2);
        cloud.startAnimation(cloud_moving);

        play = findViewById(R.id.play);
        learn = findViewById(R.id.learn);
        sounds = findViewById(R.id.mute);



        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down);

        Glide.with(this).load(R.drawable.butterfly).into(butt);

        learn.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                learn.startAnimation(scaleUp);
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                play.setClickable(false);
                learn.startAnimation(scaleDown);
                btn_sfx.start();
                Intent intent = new Intent(HomeScreen.this,FlashCardCategory.class);
                startActivity(intent);
                Animatoo.animateShrink(this);
            }
            return true;
        });

        play.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                play.startAnimation(scaleUp);
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                learn.setClickable(false);
                play.startAnimation(scaleDown);
                btn_sfx.start();
                Intent intent = new Intent(HomeScreen.this,GameCategory.class);
                startActivity(intent);
                Animatoo.animateInAndOut(this);
            }
            return true;
        });
    }

    private void seekFunction() {
        seekBar.setMax(maxVolume);
        seekBar.setProgress(currentVol);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void Mute(View view) {
        if (BackgroundSoundService.player.isPlaying()){
            sounds.setImageResource(R.drawable.off_sounds);
           BackgroundSoundService.player.pause();
        }else {
            sounds.setImageResource(R.drawable.speaker);
            BackgroundSoundService.player.start();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN){
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                seekBar.setProgress(currentVol);
            }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN){
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                    currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    seekBar.setProgress(currentVol);
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopService(new Intent(this,BackgroundSoundService.class));
    }



}