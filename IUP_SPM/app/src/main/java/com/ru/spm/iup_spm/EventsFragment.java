package com.ru.spm.iup_spm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsFragment extends Fragment {
    public ProgressBar progressBar;
    public ListView mylstEvents;
    public List<Event> myEvents;
    public ImageView imgMyLogo;
    public TextView txtNoEvents;
    public TextView txtCreateEvent;

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        imgMyLogo =(ImageView) view.findViewById(R.id.ImgMyLogo);
        txtNoEvents = (TextView) view.findViewById(R.id.NoEvents);
        progressBar = (ProgressBar) view.findViewById(R.id.myLoadingLogin);
        txtCreateEvent = (TextView) view.findViewById(R.id.txtCreate);
        mylstEvents = (ListView) view.findViewById(R.id.listmyEvents);

        reload_my_events();

        txtCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_createEvent();
            }
        });
        return view;
    }

    private void open_createEvent() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CreateEventActivity.class);
        getActivity().startActivity(intent);
    }

    private void reload_my_events() {
        ArrayList<Event> arrayMyEvents = new ArrayList<>();
        SharedPreferences preferences = this.getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String kennitala = preferences.getString("kennitala","");
        Log.e("kennitala",""+kennitala);
        progressBar.setVisibility(View.VISIBLE);
        imgMyLogo.setVisibility(View.INVISIBLE);
        txtCreateEvent.setVisibility(View.INVISIBLE);
        txtNoEvents.setVisibility(View.INVISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://iuppartyservice.azurewebsites.net/api/event/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserService userService = retrofit.create(UserService.class);
        Call<List<Event>> listCall = userService.getMyEvents(kennitala);
        listCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                myEvents = response.body();

                for (Event e : myEvents){
                    arrayMyEvents.add(new Event(
                            e.getImage(),
                            e.getName(),
                            e.getMaxPeople(),
                            e.getParticipants()
                    ));
                }
                if(arrayMyEvents.isEmpty()){
                    imgMyLogo.setVisibility(View.VISIBLE);
                    txtCreateEvent.setVisibility(View.VISIBLE);
                    txtNoEvents.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                MyEventAdapter myEventAdapter = new MyEventAdapter(getContext(),R.layout.list_myevent,arrayMyEvents);
                mylstEvents.setAdapter(myEventAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("ERROR","error");
                progressBar.setVisibility(View.INVISIBLE);
                imgMyLogo.setVisibility(View.VISIBLE);
                txtCreateEvent.setVisibility(View.VISIBLE);
                txtNoEvents.setVisibility(View.VISIBLE);
            }
        });
    }
}