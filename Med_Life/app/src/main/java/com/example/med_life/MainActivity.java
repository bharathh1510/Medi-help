package com.example.med_life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View view) {
    }

    public void signup(View view) {
        startActivity(new Intent(MainActivity.this,Create.class));
    }



    public void save(View view) {
    }

    public void back(View view) {
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }

    }