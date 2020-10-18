package com.ru.spm.iup_spm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    Button button_back;
    Button button_sign;
    EditText Name, Password, Kennitala, Surname, Birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        button_back = (Button)findViewById(R.id.back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                open_main();
            }}
        );
        button_sign = (Button)findViewById(R.id.button_sign_up);
        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(Kennitala.getText().toString()) || TextUtils.isEmpty(Name.getText().toString()) || TextUtils.isEmpty(Surname.getText().toString()) ||
                        TextUtils.isEmpty(Password.getText().toString()) || TextUtils.isEmpty(Birthday.getText().toString() )){
                    String message = "All inputs are required!";
                    Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
                }else{
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setKennitala(Kennitala.getText().toString());
                    registerRequest.setName(Name.getText().toString());
                    registerRequest.setSurname(Surname.getText().toString());
                    registerRequest.setPassword(Password .getText().toString());
                    registerRequest.setBirthday(Birthday.getText().toString());
                    registerUser(registerRequest);
                }
            }
        });
        Name = (EditText) findViewById(R.id.textname);
        Surname = (EditText) findViewById(R.id.textSurname);
        Password = (EditText) findViewById(R.id.textPassword);
        Birthday = (EditText) findViewById(R.id.textBirthday);
        Kennitala = (EditText) findViewById(R.id.textkennitala);


    }
    private void open_main() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }

    public void registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    String message = "Successful.";
                    Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();

                    startActivity(new Intent(Signup.this,Login.class));
                    finish();
                }else{
                    String message = "An error occurred please try later.";
                    Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }
}