package com.ru.spm.iup_spm;

import android.content.Context;
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

        imgEvent.setImageResource(getItem(position).getImgEvent());
        imgProfile.setImageResource(getItem(position).getImgHostEvent());

        txtDistance.setText(getItem(position).getDistanceEvent());
        txtEvent.setText(getItem(position).getNameEvent());
        txtProfile.setText(getItem(position).getNameHostEvent());

        return convertView;
    }
}