package com.example.madpropertypal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity implements View.OnClickListener {


    Button registerBt;
    TextInputLayout fNameTIL, emailTIL,passwordTIL;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        init();

    }

    private void init() {
        registerBt = findViewById(R.id.btnRegister);
        registerBt.setOnClickListener(this::onClick);

        fNameTIL = findViewById(R.id.TILfName);
        emailTIL = findViewById(R.id.TILEmailAddress);
        passwordTIL = findViewById(R.id.TILPassword);


        mAuth = FirebaseAuth.getInstance();

        userDBRef = FirebaseDatabase.getInstance().getReference("Users");


    }





    DatabaseReference userDBRef;
   String fName, email, password;
    @Override
    public void onClick(View v) {

        if (v == registerBt){

            fName = fNameTIL.getEditText().getText().toString().toString();
            email = emailTIL.getEditText().getText().toString().toString();
            password = passwordTIL.getEditText().getText().toString().toString();

            if (fName.isEmpty() || email.isEmpty() || password.isEmpty()){

                Toast.makeText(Registration.this, "Missing Required Values!!", Toast.LENGTH_SHORT).show();

            }else {

                mAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Registration.this, "Something went wrong "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        String uid = mAuth.getCurrentUser().getUid();

                        HashMap map = new HashMap();
                        map.put("FirstName", fName);
                        map.put("email",email);


                        userDBRef.child(uid).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                startActivity(new Intent(getBaseContext(), LoginActivity.class));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(Registration.this, "Something went wrong "+ e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                });
            }

        }
    }
}