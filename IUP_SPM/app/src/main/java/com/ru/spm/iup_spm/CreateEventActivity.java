package com.ru.spm.iup_spm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CreateEventActivity extends AppCompatActivity {
    private Button btnsetting;
    private ImageView imgIcon;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        btnsetting = (Button) findViewById(R.id.btnSettings);
        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_settings();
            }
        });

        imgIcon = findViewById(R.id.logo);
        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_home();
            }
        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_home();
            }
        });
    }

    private void open_home() {
        startActivity(new Intent(this, Home.class));
    }

    private void open_settings() {
        startActivity(new Intent( this,SettingsActivity.class));
    }
}