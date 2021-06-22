package com.example.med_life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {
    TabLayout tl;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tl= findViewById(R.id.tl);
        vp=findViewById(R.id.vp);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager(), 0) {

            @Override
            public Fragment getItem(int position) {
                if (position==0){
                    return new BookNow();
                }
                if(position==1){
                    return new Upcoming();
                }
                return null;
            }

            @Override
            public int getCount() {
                return title.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tl.setupWithViewPager(vp);
    }
    }
abstract class MyAdapter extends FragmentPagerAdapter{
    String[] title={"Book Now","Upcoming"};

    public MyAdapter(FragmentManager fm, int i) {
        super(fm,i);
    }
}