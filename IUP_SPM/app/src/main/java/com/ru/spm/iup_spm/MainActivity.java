package com.ru.spm.iup_spm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonSignIn;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSignUp = (Button) findViewById(R.id.signup_main);
        buttonSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        buttonSignIn = (Button) findViewById(R.id.signin_main);
        buttonSignIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    private void openSignUp() {
        Intent intent_sign_up = new Intent(this,Signup.class);
        startActivity(intent_sign_up);
    }

    private void openLogin() {
        Intent intent_login = new Intent(this, Login.class);
        startActivity(intent_login);
    }
}