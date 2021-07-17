package com.example.medigo;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
    Context ct;
    int[] image;
    String[] hospitals;
    String[] price1;
    String[] price2;
    int total;

    public ItemAdapter(Context ct, int[] image,  String[] hospitals, String[] price1, String[] price2) {
        this.ct = ct;
        this.image = image;
        this.hospitals = hospitals;
        this.price1 = price1;
        this.price2 = price2;
    }

    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(ct).inflate(R.layout.layout,parent,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }




    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemAdapter.MyViewHolder holder, int position) {
        holder.iv.setImageResource(image[position]);
        holder.tv1.setText(hospitals[position]);
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b= new AlertDialog.Builder(v.getContext());
                View d= LayoutInflater.from(b.getContext()).inflate(R.layout.hospital,null,false);
                TextView hospitalname= d.findViewById(R.id.hospitalname);
                TextView hname=d.findViewById(R.id.hname);
                TextView hnum=d.findViewById(R.id.hnum);
                TextView hospitalnum=d.findViewById(R.id.hospitalnum);
                TextView hmail=d.findViewById(R.id.hemail);
                TextView hospitalmail=d.findViewById(R.id.hospitalemail);
                TextView haddress=d.findViewById(R.id.haddress);
                TextView hospitaladdress=d.findViewById(R.id.hospitaladdress);
                //hospitalname= holder.tv1.getText(hospitals[position]);

                b.setView(d);
                b.setCancelable(false);
                b.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                b.show();

            }
        });
        holder.tv2.setText("Beds, Price is: ");
        holder.tv3.setText("O2, Price is: ");
        holder.pv1.setText(price1[position]);
        holder.pv2.setText(price2[position]);
        holder.check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //int p1= Integer.parseInt(holder.pv1.getText().toString());
                    //total=total+p1;
                }
                else{
                    total=total;
                }
            }
        });
        holder.check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //int p2=Integer.parseInt(holder.pv2.getText().toString());
                    //total=total+p2;
                }
                else{
                    total=total;
                }
            }
        });

    //Intent i=new Intent(this,BookNow.class);
    //i.putExtra(String.valueOf(total),1);

    }

    @Override
    public int getItemCount() {
        return hospitals.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1,tv2,tv3,pv1,pv2;
        CheckBox check1,check2;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.image);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            tv3=itemView.findViewById(R.id.tv3);
            pv1=itemView.findViewById(R.id.p1);
            pv2=itemView.findViewById(R.id.p2);
            check1=itemView.findViewById(R.id.beds);
            check2=itemView.findViewById(R.id.o2);
        }
    }
}
