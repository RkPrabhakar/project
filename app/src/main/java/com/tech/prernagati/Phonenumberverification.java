package com.tech.prernagati;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.Objects;


public class  Phonenumberverification extends AppCompatActivity {

    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumberverification);

        //hooks
        scrollView = findViewById(R.id.signup_3rd_screen_scroll_view);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.signup_phone_number);

    }

    public void callVerifyOTPScreen(View view) {

        if (!validatePhoneNumber()) {
            return;
        }




    //get all values passes from previous screens

    String _fullName = getIntent().getStringExtra("fullName");
    String _email = getIntent().getStringExtra("email");
    String _username = getIntent().getStringExtra("username");
    String _password = getIntent().getStringExtra("password");
    String _date = getIntent().getStringExtra("date");
    String _gender = getIntent().getStringExtra("gender");
    String _getUserEnteredPhoneNumber = Objects.requireNonNull(phoneNumber.getEditText()).toString().trim();
    String _phoneNO = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;

    Intent intent = new Intent(getApplicationContext(), OtpVerification.class);

        intent.putExtra("fullName",_fullName);
        intent.putExtra("email",_email);
        intent.putExtra("username",_username);
        intent.putExtra("password",_password);
        intent.putExtra("date",_date);
        intent.putExtra("gender",_gender);

        android.util.Pair[] pairs = new android.util.Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Phonenumberverification.this, pairs);
        startActivity(intent, options.toBundle());

    }
    public boolean validatePhoneNumber(){

        String val = Objects.requireNonNull(phoneNumber.getEditText()).getText().toString().trim();
        String checkphonenumber = "[+][0-9]{2}[' ][1-9]{2}[' ][1-9]*?\"";

        if (val.isEmpty()){
            phoneNumber.setError("Field can not be empty!");
            return false;

        } else if (val.length()>13) {
            phoneNumber.setError("Invalid Number");
            return false;
        } else if (!val.matches(checkphonenumber)){

            phoneNumber.setError("Invalid Mail");
            return false;
        }
        else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;

        }


    }


}









