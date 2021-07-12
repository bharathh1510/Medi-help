package com.example.medigo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context ct1;
    int[] image1;
    String[] names,location;

    public Adapter(Context c, int[] images, String[] names,String[] location) {
        this.ct1=c;
        this.image1=images;
        this.names=names;
        this.location=location;
    }


    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(ct1).inflate(R.layout.layout_pharmacy,parent,false);
        Adapter.MyViewHolder holder=new Adapter.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.MyViewHolder holder, int position) {
        holder.iv.setImageResource(image1[position]);
        holder.tv1.setText(names[position]);
        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= location[position];
                Intent intent = new Intent(Intent. ACTION_VIEW, Uri.parse(url));
                ct1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView iv;
    TextView tv1,tv2;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.mediImage);
            tv1=itemView.findViewById(R.id.mediname);
            tv2=itemView.findViewById(R.id.location);
        }
    }
}
