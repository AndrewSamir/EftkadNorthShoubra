package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.samir.andrew.eftkadnorthshoubra.R;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();

        checkLogin();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void checkLogin() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    startActivity(new Intent(SplashScreen.this, HomeActivity.class));

                } else {
                    // User is signed out
                    startActivity(new Intent(SplashScreen.this, Login.class));

                }
                // ...
            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}


