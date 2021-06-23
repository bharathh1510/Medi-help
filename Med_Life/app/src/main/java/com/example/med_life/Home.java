package com.example.med_life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    TabLayout tl;
    ViewPager vp;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tl= findViewById(R.id.tl);
        vp=findViewById(R.id.vp);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(this,Home.class));
            finish();
        }
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

    public void home(View view) {
        startActivity(new Intent(this, Home.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this,"Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact:
                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;


        }
        return true;
    }
}
abstract class MyAdapter extends FragmentPagerAdapter{
    String[] title={"Book Now","Upcoming"};

    public MyAdapter(FragmentManager fm, int i) {
        super(fm,i);
    }
}