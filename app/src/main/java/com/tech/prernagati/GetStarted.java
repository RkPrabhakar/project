package com.tech.prernagati;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GetStarted extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_get_started);

        Button button = findViewById(R.id.GetIn);
        button.setOnClickListener(view -> openLogin());

    }

    private void openLogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

}
