package com.ru.spm.iup_spm;

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

public class EventAdapter extends ArrayAdapter<Event> {
    private Context eContext;
    private int eResource;

    public EventAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Event> objects) {
        super(context, resource, objects);
        this.eResource = resource;
        this.eContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(eContext);
        convertView = layoutInflater.inflate(eResource,parent,false);

        ImageView imgEvent = convertView.findViewById(R.id.imgEvent);
        ImageView imgProfile = convertView.findViewById(R.id.imgProfile);

        TextView txtEvent = convertView.findViewById(R.id.txtNameEvent);
        TextView txtDistance = convertView.findViewById(R.id.txtDistanceEvent);
        TextView txtProfile = convertView.findViewById(R.id.txtNameProfile);
        TextView txtMaxPeople = convertView.findViewById(R.id.txtMPeople);
        TextView txtParticipants = convertView.findViewById(R.id.txtParticipants);

        imgEvent.setImageBitmap(BitmapFactory.decodeByteArray((Base64.decode(getItem(position).getImage(), Base64.DEFAULT)),0, (Base64.decode(getItem(position).getImage(), Base64.DEFAULT).length)));
        imgProfile.setImageResource(R.drawable.profile);

        txtDistance.setText(String.valueOf(getItem(position).getDistance()));
        txtEvent.setText(getItem(position).getName());
        txtProfile.setText(getItem(position).getHostName());
        txtParticipants.setText(String.valueOf(getItem(position).getParticipants()));
        txtMaxPeople.setText(String.valueOf(getItem(position).getMaxPeople()));
        return convertView;
    }
}