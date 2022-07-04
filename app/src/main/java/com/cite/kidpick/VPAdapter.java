package com.cite.kidpick;

import android.content.Context;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MyModel> modelArrayList;
    Animation shake;

    public VPAdapter(Context context, ArrayList<MyModel> modelArrayList, Animation shake) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.shake = shake;
    }

    @NonNull
    @Override
    public VPAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VPAdapter.MyViewHolder holder, int position) {
        MyModel model = modelArrayList.get(position);
        String myTitle = model.getTitle();
        int image = model.getImage();

        holder.banner.setImageResource(image);
        holder.banner.startAnimation(shake);
        holder.title.setText(myTitle);
        holder.cv.setOnClickListener(view -> holder.banner.startAnimation(shake));

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
       public ImageView banner;
        TextView title;
        CardView cv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

             banner = itemView.findViewById(R.id.banner);
             title = itemView.findViewById(R.id.title);
             cv = itemView.findViewById(R.id.cv);
        }
    }
}
