package com.example.medigo;

import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.getIntent;

public class BookNow extends Fragment  {
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_book_now, container, false);
        rv=v.findViewById(R.id.recyclerview);
        //total=getIntent().getExtras().getInt(total);
        int[] images={R.drawable.bindu,R.drawable.koti,R.drawable.rainbow,R.drawable.apollo,R.drawable.rudra};
        String[] hospital= {"Bindhu Hospital", "Koti General Hospital", "Rainbow Hospital", "Apollo Hospital", "Rudra Hospital"};
        String[] p1={"5000","2000","3000","4400","4500"};
        String[] p2={"100000","15500","12500","13500","15550"};

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemAdapter adapter = new ItemAdapter(getActivity(),images,hospital,p1,p2);
        rv.setAdapter(adapter);
        return v;
    }
    public void pay(View view){


    }

}