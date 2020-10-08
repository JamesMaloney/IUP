package com.ru.spm.iup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {
    Button button_sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button_sign = (Button)findViewById(R.id.button_sign_up);
        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_signup();
            }
        });
    }

    private void open_signup() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}