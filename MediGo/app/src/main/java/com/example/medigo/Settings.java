package com.example.medigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void update(View view) {
        Toast.makeText(this, "Details updated", Toast.LENGTH_SHORT).show();
    }

    public void cancel(View view) {
        startActivity(new Intent(this,Home.class));
    }
}