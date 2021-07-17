package com.example.medigo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class Register extends AppCompatActivity {
    EditText name,mail, pass,repass,number,address;
    TextView dob;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        number=findViewById(R.id.number);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        repass=findViewById(R.id.repass);
        address=findViewById(R.id.address);
        dob=findViewById(R.id.dob);
        auth=FirebaseAuth.getInstance();
    }
    //public void address(View view) { }
    public void dob(View view) {
        Calendar c=Calendar.getInstance();
        int year,month,day;
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
        dpd.show();
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
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(Register.this,Home.class));
                                finish();
                            }else{
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

            );
        }
    }


}
