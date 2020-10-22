package com.ru.spm.iup_spm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {
    ListView lstEvents;


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

        lstEvents = (ListView) view.findViewById(R.id.listEvents);
        ArrayList<Event> arrayEvents = new ArrayList<>();

        //TEST of LIST
        arrayEvents.add(new Event(R.drawable.party1,"Great Party","1.7km","Smetz",R.drawable.profile));
        arrayEvents.add(new Event(R.drawable.party2,"Fantastic Party","2km","Willy",R.drawable.profile));
        arrayEvents.add(new Event(R.drawable.party1,"G Party","9km","G",R.drawable.profile));
        arrayEvents.add(new Event(R.drawable.party2,"Cool Party","12km","Jacky",R.drawable.profile));

        //Custom adapter
        EventAdapter eventAdapter = new EventAdapter(Objects.requireNonNull(getContext()),R.layout.list_events,arrayEvents);
        lstEvents.setAdapter(eventAdapter);

        Button btn = (Button) view.findViewById(R.id.add_event);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_create();
            }
        });
        return view;
    }

    private void open_create() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CreateEventActivity.class);
        getActivity().startActivity(intent);
    }
}