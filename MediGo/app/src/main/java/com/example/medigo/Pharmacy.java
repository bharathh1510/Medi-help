package com.example.medigo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Pharmacy extends Fragment {
RecyclerView rv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1=inflater.inflate(R.layout.fragment_pharmacy, container, false);
        rv1=v1.findViewById(R.id.recyclerview1);
        int[] images={R.drawable.aditya,R.drawable.apolo,R.drawable.rainbo,R.drawable.walmart,R.drawable.wellcare};
        String[] names={"Aditya Pharmacy","Apollo Pharmacy","Rainbow Pharmacy","Walmart Pharmacy","Wellcare Pharmacy"};
        String[] location={"https://g.co/kgs/155evo","https://maps.app.goo.gl/WkYb5c9CavCUACdF9","https://maps.app.goo.gl/MXGJhkfSWmcKPm5Y8",
                "https://maps.app.goo.gl/e7JRhiWG5ZxZrRGr9","https://maps.app.goo.gl/9euqRAjHN9oQuH1BA"};
        rv1.setLayoutManager(new LinearLayoutManager(getContext()));
        Adapter adapter = new Adapter(getActivity(),images,names,location);
        rv1.setAdapter(adapter);
        return v1;
    }
}