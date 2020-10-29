package com.ru.spm.iup_spm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    Button button_back, button_sign;
    EditText Name, Password, Kennitala, Surname, Birthday;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name = (EditText) findViewById(R.id.textname);
        Surname = (EditText) findViewById(R.id.textSurname);
        Password = (EditText) findViewById(R.id.textPassword);
        Birthday = (EditText) findViewById(R.id.textBirthday);
        Kennitala = (EditText) findViewById(R.id.textkennitala);
        spinner = (ProgressBar)findViewById(R.id.LoadingLogin);
        button_sign = (Button)findViewById(R.id.button_sign_up);
        button_back = (Button)findViewById(R.id.back);

        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                open_main();
            }}
        );

        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(Kennitala.getText().toString()) || TextUtils.isEmpty(Name.getText().toString()) || TextUtils.isEmpty(Surname.getText().toString()) ||
                        TextUtils.isEmpty(Password.getText().toString()) || TextUtils.isEmpty(Birthday.getText().toString() )){
                    String message = "All inputs are required!";
                    Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
                }else if (Kennitala.getText().toString().length()!=10){
                    String message = "Kennitala not valid!";
                    Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
                }else {
                    spinner.setVisibility(View.VISIBLE);
                    button_sign.setClickable(false);
                    button_back.setClickable(false);
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
    }
    private void open_main() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = ApiClient.getServiceRegister().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    String message = "Successful! User correctly created!";
                    Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Signup.this,Login.class));
                    finish();
                } else {
                    String message = "An error occurred please try later.";
                    Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
                }
                spinner.setVisibility(View.INVISIBLE);
                button_sign.setClickable(true);
                button_back.setClickable(true);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = "Successful.";
                Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
                spinner.setVisibility(View.INVISIBLE);
                button_sign.setClickable(true);
                button_back.setClickable(true);
                startActivity(new Intent(Signup.this,Login.class));
                finish();
                //String message = t.getLocalizedMessage();
                //Toast.makeText(Signup.this, message,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}