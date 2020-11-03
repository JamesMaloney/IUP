package com.ru.spm.iup_spm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.Rating;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PartyActivity extends AppCompatActivity {

    RatingBar ratingBarParty;
    ProgressBar progressBarParty;
    ImageView imgParty;
    TextView txtPartyName;
    TextView txtPartyHostname;
    TextView txtPartyDateStart;
    TextView txtPartyDateEnd;
    TextView txtPartyDistance;
    TextView txtPartyDescription;
    TextView txtPartyParticipants;
    Button btnJoinParty;
    String rating;
    Event event;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        get_rating_user();

        ratingBarParty = (RatingBar) findViewById(R.id.ratingBarParty);
        progressBarParty = (ProgressBar) findViewById(R.id.LoadingParty);
        btnJoinParty = (Button) findViewById(R.id.btnJoinParty);
        txtPartyName = (TextView) findViewById(R.id.txtNameParty);
        txtPartyHostname = (TextView) findViewById(R.id.txtHostnameParty);
        txtPartyDateStart = (TextView) findViewById(R.id.txtPartyDateStart);
        txtPartyDateEnd = (TextView) findViewById(R.id.txtPartyDateEnd);
        txtPartyDistance = (TextView) findViewById(R.id.txtDistanceParty);
        txtPartyDescription = (TextView) findViewById(R.id.txtPartyDescription);
        txtPartyParticipants = (TextView) findViewById(R.id.txtPartyParticipants);
        imgParty = (ImageView) findViewById(R.id.imgParty);
        btnBack = (Button) findViewById(R.id.btnBackParty);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_home();
            }
        });

        String eventID = getIntent().getStringExtra("eventID");

        get_party(eventID);

        btnJoinParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join_party(eventID);
            }
        });
    }

    private void open_home() {
        Intent intent = new Intent(PartyActivity.this,Home.class);
        startActivity(intent);
        finish();
    }

    private void join_party(String eventID) {
        progressBarParty.setVisibility(View.VISIBLE);
        btnJoinParty.setClickable(false);
        String url = "https://iuppartyservice.azurewebsites.net/api/event/"+eventID+"/";
        SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String kennitala = preferences.getString("kennitala","");
        String hostname = preferences.getString("hostname","");

        JoinParty joinParty = new JoinParty(kennitala,hostname," ");
        Call<String> EventRequestCall = ApiClient.getServiceJoinParty(url).joinParty(joinParty);
        EventRequestCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    response.body();
                    String message = "Event Joined!!!";
                    Toast.makeText(PartyActivity.this, message,Toast.LENGTH_LONG).show();
                    open_home();
                } else {
                    String message = "You can't join again!!";
                    Toast.makeText(PartyActivity.this, message,Toast.LENGTH_LONG).show();
                }
                progressBarParty.setVisibility(View.INVISIBLE);
                btnJoinParty.setClickable(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String message = "Event Joined!!!";
                Toast.makeText(PartyActivity.this, message,Toast.LENGTH_LONG).show();
                progressBarParty.setVisibility(View.INVISIBLE);
                btnJoinParty.setClickable(true);
                Intent intent = new Intent(PartyActivity.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void get_party(String eventID){
        SharedPreferences preferences = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        progressBarParty.setVisibility(View.VISIBLE);
        String lati = preferences.getString("latitude","");
        String longi = preferences.getString("longitude","");
        String url = "https://iuppartyservice.azurewebsites.net/api/event/"+eventID+"/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserService userService = retrofit.create(UserService.class);
        Call<Event> listCall = userService.getParty(lati,longi);
        listCall.enqueue(new Callback<Event>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(!response.isSuccessful()){
                    String message = "Request Event Failed.";
                    Toast.makeText(PartyActivity.this, message,Toast.LENGTH_LONG).show();
                    progressBarParty.setVisibility(View.INVISIBLE);
                    return;
                }
                event = response.body();
                /* ASSIGNMENTS */
                txtPartyDateEnd.setText(event.getDateEnd());
                txtPartyDateStart.setText(event.getDateStart());
                txtPartyDescription.setText(event.getDescription());
                txtPartyDistance.setText(String.valueOf(event.getDistance()));
                txtPartyHostname.setText(event.getHostName());
                txtPartyName.setText(event.getName());
                //DECODE STRING TO BITMAP
                imgParty.setImageBitmap(BitmapFactory.decodeByteArray((Base64.decode(event.getImage(), Base64.DEFAULT)),0, (Base64.decode(event.getImage(), Base64.DEFAULT).length)));
                progressBarParty.setVisibility(View.INVISIBLE);
                ratingBarParty.setRating(Float.parseFloat(rating));
                txtPartyParticipants.setText(event.getParticipants()+" / "+event.getMaxPeople());
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                String message = "Event GET Error.";
                Toast.makeText(PartyActivity.this, message,Toast.LENGTH_LONG).show();
                progressBarParty.setVisibility(View.INVISIBLE);
            }
        });
    }



    public void get_rating_user(){
        SharedPreferences preferences = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String kennitala = preferences.getString("kennitala","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://iupusesservice.azurewebsites.net/api/appIdentity/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserService service = retrofit.create(UserService.class);
        Call<ResponseBody> result = service.getRatingUser(kennitala);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    String message = "GET rating Failed.";
                    Toast.makeText(PartyActivity.this, message,Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    rating = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String message = "Home GET Hostname Error.";
                Toast.makeText(PartyActivity.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }
}