package com.cite.kidpick;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    List<Integer> images;
    List<Integer> pos;
    int width;
    int height;
    int delay;
    private MediaPlayer cardsClosed;

    public ImageAdapter(Context context, List<Integer> images,List<Integer> pos,int width,int height,int delay,MediaPlayer cardsClosed) {
        this.context = context;
        this.images = images;
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.delay = delay;
        this.cardsClosed = cardsClosed;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if(view == null){
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(width,height));
            imageView.setPadding(5,5,5,5);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setBackgroundResource(R.drawable.image_border);

        }else imageView = (ImageView) view;


        imageView.setImageResource(images.get(pos.get(i)));
        new Handler().postDelayed(() -> {
            // imageView.setImageResource(R.drawable.front);
            cardsClosed.start();

            try {
                Glide.with(this.context).load(R.drawable.flip2).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setBackgroundResource(R.color.trans);
                    imageView.setImageResource(R.drawable.blue2);
                    cardsClosed.stop();
                }
            },200);
        },delay);



        return imageView;
    }

}