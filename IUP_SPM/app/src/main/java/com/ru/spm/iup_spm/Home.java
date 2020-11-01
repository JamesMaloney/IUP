package com.ru.spm.iup_spm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    LoginResponse loginResponse;
    private BottomNavigationView buttonNavigationView;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

        if (preferences.getString("hostname","").isEmpty()){
            getHostName();
        } else if(preferences.getString("latitude","").isEmpty()){
            getLocation();
        }
        //Button info
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_info();
            }
        });

        //Fragments!
        buttonNavigationView = findViewById(R.id.bottom_nav);
        buttonNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        //Login response TEST
        Intent intent = getIntent();
        if (intent.getExtras()!= null){
            loginResponse = (LoginResponse)intent.getSerializableExtra("data");
        }
    }

    private void open_info() {
        startActivity(new Intent(this,SettingsActivity.class));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        //FRAGMENT NAVIGATION
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment=null;
            switch (menuItem.getItemId()){
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.events:
                    fragment = new EventsFragment ();
                    break;
                case R.id.profile:
                    fragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }
    };

    public void getLocation(){
        GpsTracker gpsTracker = new GpsTracker(Home.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

            preferences.edit().putString("latitude", String.valueOf(latitude)).apply();
            preferences.edit().putString("longitude", String.valueOf(longitude)).apply();
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public void getHostName(){
        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String kennitala = preferences.getString("kennitala","");

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://iupusesservice.azurewebsites.net/api/appIdentity/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        UserService service = retrofit.create(UserService.class);
        Call<ResponseBody> result = service.getHostName(kennitala);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    return;
                }
                try {
                    String hostname = response.body().string();
                    preferences.edit().putString("hostname", hostname).apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String message = "Home GET Hostname Error.";
                Toast.makeText(Home.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }
}