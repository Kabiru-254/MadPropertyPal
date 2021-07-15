package com.example.madpropertypal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    private Button Next;
    private Handler handler = new Handler();
    private int SLEEP_DURATION = 3000;
    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        UserID = FirebaseAuth.getInstance().getUid();

        final FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser =mAuth.getCurrentUser();

                //sleep for 3 seconds then move to home page;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (firebaseUser==null)
                        {
                            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {

                            Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                },SLEEP_DURATION);




            }
        };

        authStateListener.onAuthStateChanged(mAuth);

    }
}