package com.tech.prernagati;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();


        Button button = findViewById(R.id.login);
        button.setOnClickListener(view -> open_loginScreen());

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(view -> open_signupScreen());

    }
    private void open_loginScreen() {
        Intent intent = new Intent(this, loginbutton.class);
        startActivity(intent);
    }

    private void open_signupScreen() {
        Intent intent1 = new Intent(this, Sign_up.class);
        startActivity(intent1);

        }

    }

