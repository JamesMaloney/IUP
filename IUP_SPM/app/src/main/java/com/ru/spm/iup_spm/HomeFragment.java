package com.ru.spm.iup_spm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    public ListView lstEvents;
    public List<Event> events;
    ProgressBar progressBar;
    Button btnReload;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.LoadingLogin);
        lstEvents = (ListView) view.findViewById(R.id.listEvents);

        //TEST of LIST
/*
        arrayEvents.add(new Event(String.valueOf(R.drawable.party1),"Great Party",1,"Smetz",1,String.valueOf(R.drawable.profile),false));
        arrayEvents.add(new Event(String.valueOf(R.drawable.party2),"Fantastic Party",2,"Willy",4,String.valueOf(R.drawable.profile),false));
        arrayEvents.add(new Event(String.valueOf(R.drawable.party1),"G Party",9,"G",6,String.valueOf(R.drawable.profile),false));
        arrayEvents.add(new Event(String.valueOf(R.drawable.party2),"Cool Party",12,"Jacky",10,String.valueOf(R.drawable.profile),false));
*/
        btnReload = (Button) view.findViewById(R.id.reload_event);
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload_events();
            }
        });

        Button btnAddEvent = (Button) view.findViewById(R.id.add_event);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_create();
            }
        });
        return view;
    }

    private void reload_events() {
        ArrayList<Event> arrayEvents = new ArrayList<>();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String lati = preferences.getString("latitude","");
        String longi = preferences.getString("longitude","");
        Log.e("long",""+longi);
        Log.e("lat",""+lati);
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://iuppartyservice.azurewebsites.net/api/event/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Log.e("RETRO", String.valueOf(retrofit));
        UserService userService = retrofit.create(UserService.class);
        Call<List<Event>> listCall = userService.getEvents(lati,longi);
        listCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                events = response.body();
                for (Event e : events){
                    arrayEvents.add(new Event(
                            e.getImage(),
                            e.getName(),
                            e.getDistance(),
                            e.getHostName(),
                            e.getMaxPeople(),
                            e.getParticipants(),
                            e.getImgHostEvent(),
                            e.getHidden()
                    ));
                }
                EventAdapter eventAdapter = new EventAdapter(getContext(),R.layout.list_events,arrayEvents);
                lstEvents.setAdapter(eventAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("ERROR","error");
            }
        });
    }

    private void open_create() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CreateEventActivity.class);
        getActivity().startActivity(intent);
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

}