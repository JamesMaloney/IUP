package com.ru.spm.iup_spm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyEventAdapter extends ArrayAdapter<Event> {
    private Context eContext;
    private int eResource;

    public MyEventAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Event> objects) {
        super(context, resource, objects);
        this.eResource = resource;
        this.eContext = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(eContext);
        convertView = layoutInflater.inflate(eResource,parent,false);

        ImageView imgEvent = convertView.findViewById(R.id.imgmyEvent);
        TextView txtEvent = convertView.findViewById(R.id.txtMyEvent);
        TextView txtMaxParticipants = convertView.findViewById(R.id.txtMyMaxPartecipants);
        TextView txtParticipants = convertView.findViewById(R.id.txtMyParticipants);

        imgEvent.setImageBitmap(BitmapFactory.decodeByteArray((Base64.decode(getItem(position).getImage(), Base64.DEFAULT)),0, (Base64.decode(getItem(position).getImage(), Base64.DEFAULT).length)));
        txtEvent.setText(getItem(position).getName());
        txtParticipants.setText(String.valueOf(getItem(position).getParticipants()));
        txtMaxParticipants.setText(String.valueOf(getItem(position).getMaxPeople()));
        return convertView;
    }

}