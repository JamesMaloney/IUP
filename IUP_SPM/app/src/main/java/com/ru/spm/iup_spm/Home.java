package com.ru.spm.iup_spm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    LoginResponse loginResponse;
    private BottomNavigationView buttonNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonNavigationView = findViewById(R.id.bottom_nav);
        buttonNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        Intent intent = getIntent();
        if (intent.getExtras()!= null){
            loginResponse = (LoginResponse)intent.getSerializableExtra("data");
            Log.e("TAG","=========>>>> "+loginResponse.getToken());
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
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
}