package com.ru.spm.iup_spm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button button_back, button_login;
    EditText Kennitala, Password;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = (ProgressBar)findViewById(R.id.LoadingLogin);

        button_back = (Button)findViewById(R.id.back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                open_main();
            }}
        );
        button_login = (Button)findViewById(R.id.login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(Kennitala.getText().toString()) || TextUtils.isEmpty(Password.getText().toString())){
                    String message = "All inputs are required!";
                    Toast.makeText(Login.this, message,Toast.LENGTH_LONG).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setKennitala(Kennitala.getText().toString());
                    loginRequest.setPassword(Password.getText().toString());
                    loginUser(loginRequest);
                    spinner.setVisibility(View.VISIBLE);
                    button_login.setClickable(false);
                    button_back.setClickable(false);
                }
            }
        });
        Kennitala = (EditText)findViewById(R.id.kennitala);
        Password = (EditText)findViewById(R.id.password);

    }

    private void open_main() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginRequestCall = ApiClient.getServiceLogin().loginUser(loginRequest);
        loginRequestCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    response.body();
                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setToken(response.body().getToken());
                    loginResponse.setName(response.body().getName());
                    loginResponse.setKennitala(response.body().getKennitala());

                    Intent intent = new Intent(Login.this,Home.class);
                    intent.putExtra("data",loginResponse);

                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                    preferences.edit().putString("token", loginResponse.getToken()).apply();
                    preferences.edit().putString("kennitala", loginResponse.getKennitala()).apply();

                    spinner.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                    finish();
                } else {
                    String message = "An error during login occurred please try later.";
                    Toast.makeText(Login.this, message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = "Failed";
                Toast.makeText(Login.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public void onClick(View v) {
        }

}
