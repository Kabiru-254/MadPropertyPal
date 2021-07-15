package com.example.madpropertypal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    TextInputLayout emailTIL, passwordTIL;
    TextView forgotTV, registerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();
    }



    FirebaseAuth mAuth;

    private void init() {

        login = findViewById(R.id.btnLogin);
        emailTIL = findViewById(R.id.TILEmailLogin);
        passwordTIL = findViewById(R.id.TILPasswordLogin);
        registerTV = findViewById(R.id.tvRegister);
        forgotTV = findViewById(R.id.tvForgotPassword);
        forgotTV.setOnClickListener(this::onClick);
        login.setOnClickListener(this::onClick);
        registerTV.setOnClickListener(this::onClick);


        mAuth = FirebaseAuth.getInstance();
    }








    String email,password;
    @Override
    public void onClick(View v) {
        if (v == login){

            email = emailTIL.getEditText().getText().toString().trim();
            password = passwordTIL.getEditText().getText().toString().trim();


            if (email.isEmpty() ||  password.isEmpty()){


                Toast.makeText(this, "Required Fields missing", Toast.LENGTH_SHORT).show();

            }else {

                mAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        startActivity(new Intent(getBaseContext(),HomeActivity.class));
                    }
                });

            }

        }else if (v == forgotTV){
            passwordTIL.setVisibility(View.GONE);
            email = emailTIL.getEditText().getText().toString().trim();

            if (email.isEmpty()){
                Toast.makeText(this, "Required Fields missing", Toast.LENGTH_SHORT).show();


            }else {

                 mAuth.sendPasswordResetEmail(email).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {

                         Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                     }
                 }).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {
                         passwordTIL.setVisibility(View.VISIBLE);
                         Toast.makeText(LoginActivity.this, "Email reset link has been sent to your email", Toast.LENGTH_SHORT).show();
                     }
                 });
            }
        }else if (v == registerTV)
        {
            Intent intent = new Intent(LoginActivity.this, Registration.class);
            startActivity(intent);
        }
    }
}