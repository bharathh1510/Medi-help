package com.example.medigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    EditText mail, pass;
    SignInButton signInButton;
    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;
    public static final int RC_SIGN_IN=1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        auth=FirebaseAuth.getInstance();
        signInButton=findViewById(R.id.signinbutton);
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(this,Home.class));
            finish();
        }
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });        mAuth=FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this,Home.class));
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Google sign-in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signup(View view) {
        startActivity(new Intent(MainActivity.this, Register.class));
    }

    public void login(View view) {
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
