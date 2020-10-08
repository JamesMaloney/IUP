package com.ru.spm.iup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity{
    Button button_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button_signin = (Button)findViewById(R.id.login);
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_signin();
            }
        });
    }

    private void open_signin() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
