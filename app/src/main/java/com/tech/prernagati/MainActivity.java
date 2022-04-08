package com.tech.prernagati;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN= 3000;
    //variables
    Animation topanim, bottomanim;
    ImageView background;
    TextView Slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        //animation
        topanim = AnimationUtils.loadAnimation(this,R.anim.topanim);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.topanim);

        //hooks
        background = findViewById(R.id.background);
        Slogan = findViewById(R.id.slogan);

        background.setAnimation(topanim);
        Slogan.setAnimation(bottomanim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this,GetStarted.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);
    }
}
