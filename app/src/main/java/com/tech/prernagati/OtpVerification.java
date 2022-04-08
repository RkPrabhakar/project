package com.tech.prernagati;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {

    PinView pinFromUser;
    String codeBySystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        pinFromUser = findViewById(R.id.pin_view);

        String _phoneNo = getIntent().getStringExtra("phoneNo");

        sendVerificationCodeToUser(_phoneNo);


    }


    private void sendVerificationCodeToUser(String phoneNo) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,    // Phone number to verify
                60L,            // Timeout and unit
                TimeUnit.SECONDS,        // Activity (for callback binding)
                (Activity) TaskExecutors.MAIN_THREAD,
                mcallbacks);// OnVerificationStateChangedCallbacks

    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        pinFromUser.setText(code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OtpVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
        .addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {

                Toast.makeText(OtpVerification.this, "Verfication Completed", Toast.LENGTH_SHORT).show();

            } else {
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(OtpVerification.this, "Verification Not Completed Try again!", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }

    public void callNextScreenFromOTP(View view) {

        String code = Objects.requireNonNull(pinFromUser.getText()).toString();
        if (!code.isEmpty()) {
            verifyCode(code);


        }
    }
}


