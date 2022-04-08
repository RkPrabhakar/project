package com.tech.prernagati;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class Sign_up extends AppCompatActivity {


    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText, slideText;

    TextInputLayout fullName, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        //Hooks for animation
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

        // hooks for getting data
        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password= findViewById(R.id.signup_password);


    }

    public void callNextSigupScreen(View view) {

        Intent intent = null;
        if (!validateFullName() | validateUsername() | !validateEmail() | validatePassword()) {
            intent = new Intent(getApplicationContext(), AgeVerification.class);
        }
        //Add Shared Animation
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");
        //pairs[4] = new Pair<View, String>(slideText, "transition_slide_text");
         ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Sign_up.this, pairs);
        startActivity(intent, options.toBundle());

    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), loginbutton.class));
        finish();
    }

    private boolean validateFullName(){
        String val = Objects.requireNonNull(fullName.getEditText()).getText().toString().trim();
        if (val.isEmpty()){
            fullName.setError("Field can not be empty!");
            return false;
        }
        else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;

        }
    }

    private boolean validateUsername(){
        String val = Objects.requireNonNull(username.getEditText()).getText().toString().trim();
        String checkspaces = "\\A\\W{4,20}\\z";
        if (val.isEmpty()){
            username.setError("Field can not be empty!");
            return false;
        } else if (val.length()>20){
            username.setError("Username is too large!");
            return false;

        } else if (!val.matches(checkspaces)){

            username.setError("No White Spaces Allowed");
            return false;
        }
        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;

        }
    }

    private boolean validateEmail(){
        String val = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
        String checkEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if (val.isEmpty()){
            email.setError("Field can not be empty!");
            return false;

        } else if (!val.matches(checkEmail)){

            email.setError("Invalid Mail");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;

        }
    }

    private boolean validatePassword() {
        String val = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
        String checkpassword = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        if (val.isEmpty()){
            password.setError("Field can not be empty!");
            return false;

        } else if (!val.matches(checkpassword)){

            password.setError("Invalid Mail");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;

        }
    }

}
