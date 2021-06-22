package com.example.med_life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this,Home.class));
    }

    public void signup(View view) {
        startActivity(new Intent(MainActivity.this,Create.class));
    }

    public void dob(View view) {
    }

    public void save(View view) {
    }

    public void back(View view) {
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }

    }