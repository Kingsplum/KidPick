package com.cite.kidpick;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<MyModel> modelArrayList;
    Animation shake,blink;
    private MediaPlayer cardClosed;







    //constructor
    public MyAdapter(Context context, ArrayList<MyModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //get data
        MyModel model = modelArrayList.get(position);
        String myTitle = model.getTitle();
        int image = model.getImage();
        blink = AnimationUtils.loadAnimation(context,R.anim.blink);
        shake = AnimationUtils.loadAnimation(context,R.anim.rotate);


        //inflate layout card_item.xml
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);

        //init uid view  card_item.xml
         ImageView banner = view.findViewById(R.id.banner);
        TextView title = view.findViewById(R.id.title);
        CardView cv = view.findViewById(R.id.cv);




        //set data
        banner.setImageResource(image);
        title.setText(myTitle);

        //set anim
        banner.startAnimation(shake);
        title.startAnimation(blink);




        //handle card click
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cards onclick laturr pag may time pa
                banner.startAnimation(shake);
            }
        });

        //add view to the container
        container.addView(view, getItemPosition(position));


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


}
