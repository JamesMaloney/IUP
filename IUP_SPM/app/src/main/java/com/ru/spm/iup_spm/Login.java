package com.ru.spm.iup_spm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button button_back;
    Button button_login;
    EditText kennitala, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_back = (Button)findViewById(R.id.back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                open_main();
            }}
        );
        button_login = (Button)findViewById(R.id.login);
        button_login.setOnClickListener(this);

        kennitala = (EditText)findViewById(R.id.kennitala);
        password = (EditText)findViewById(R.id.password);

    }

    private void open_main() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void open_signin() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_sign_up:

                break;
        }
    }
}