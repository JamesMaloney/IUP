package com.ru.spm.iup_spm;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    LoginResponse loginResponse;
    private BottomNavigationView buttonNavigationView;
    private Button btnSettings;
    private List<Event> eventTries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getLocation();

        //Button Settings
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_settings();
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
            Log.e("TAG","=========>>>> "+loginResponse.getKennitala());
        }
    }

    private void open_settings() {
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

    public void getEvents(){

    }
}