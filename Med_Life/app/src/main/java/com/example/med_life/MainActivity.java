package com.example.med_life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText name,mail, pass,repass,number,address;
    TextView dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        name=findViewById(R.id.name);
        number=findViewById(R.id.number);
        auth=FirebaseAuth.getInstance();
        repass=findViewById(R.id.repass);
        address=findViewById(R.id.address);
        dob=findViewById(R.id.dob);
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(this,Home.class));
            finish();
        }
    }

    /*public void login(View view) {
        String email=mail.getText().toString().trim();
        String password= pass.getText().toString();
        if (email.isEmpty()|password.isEmpty()){
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(MainActivity.this,Home.class));
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }*/
    public void login(View view){
        startActivity(new Intent(this,Home.class));
    }
    public void save(View view) {
        String email=mail.getText().toString().trim();
        String password= pass.getText().toString();
        String repassword= repass.getText().toString();
        String rname=name.getText().toString();
        String phone=number.getText().toString();
        String add=address.getText().toString();
        String dateob=dob.getText().toString();
        if (email.isEmpty()|password.isEmpty()|repassword.isEmpty()|rname.isEmpty()|phone.isEmpty()|add.isEmpty()|dateob.isEmpty()){
            Toast.makeText(this, "Enter all the details", Toast.LENGTH_SHORT).show();
        }else if(password.length()<6){
            pass.setError("Minimum 6 digits");
        }
        else if(!password.equals(repassword)){
            repass.setError("Not same");
        }
        else if (phone.length()!=10){
            Toast.makeText(this, "Enter correct number", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(MainActivity.this,Home.class));
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

            );
        }
    }

    public void signup(View view) {
        startActivity(new Intent(MainActivity.this,Create.class));
    }

    public void back(View view) {
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }

    public void reset(View view) {
        AlertDialog.Builder b= new AlertDialog.Builder(this);
        View v= LayoutInflater.from(this).inflate(R.layout.reset,null,false);
        EditText email=v.findViewById(R.id.editTextTextEmailAddress);
        b.setView(v);
        b.setCancelable(false);
        b.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String e=email.getText().toString();
                if(e.isEmpty()){
                    email.setError("Can't be empty");
                }else{
                    auth.sendPasswordResetEmail(e).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull  Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Reset link sent", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Email not correct", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.show();
    }
}