package com.example.medigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
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
        tl = findViewById(R.id.tl);
        vp = findViewById(R.id.vp);
        auth = FirebaseAuth.getInstance();
        vp.setAdapter(new MyAdapter(getSupportFragmentManager(),0));
        tl.setupWithViewPager(vp);

    }




    public class MyAdapter extends FragmentPagerAdapter {
        String[] title = {"Book Now", "Pharmacy"};

        public MyAdapter(FragmentManager fm, int i) {
            super(fm, i);
        }
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new BookNow();
            }
            if (position == 1) {
                return new Pharmacy();
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
                startActivity(new Intent(this, Profile.class));
                break;
            case R.id.contact:
                AlertDialog.Builder b= new AlertDialog.Builder(this);
                View v= LayoutInflater.from(this).inflate(R.layout.contact,null,false);
                TextView contact= findViewById(R.id.contactus);
                b.setView(v);
                b.setCancelable(false);
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                b.show();
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

