package com.ru.spm.iup_spm;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

    RatingBar ratingBar;
    ProgressBar progressBar;
    ImageView imgProfile;
    EditText txtProfileName;
    EditText txtProfileSurname;
    EditText txtBirthday;
    EditText txtProfileDescription;
    Button btnSave;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.ProfileLoading);
        btnSave = (Button) view.findViewById(R.id.btnSaveProfile);
        txtBirthday = (EditText) view.findViewById(R.id.txtProfileBirthday);
        txtProfileDescription = (EditText) view.findViewById(R.id.txtProfileDescription);
        txtProfileName = (EditText) view.findViewById(R.id.txtProfileName);
        txtProfileSurname = (EditText) view.findViewById(R.id.txtProfileSurname);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBarProfile);
        imgProfile = (ImageView)  view.findViewById(R.id.imgProfileChange);

        getProfile();
        return view;
    }

    private void getProfile() {
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences preferences = this.getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String kennitala = preferences.getString("kennitala","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://iupusesservice.azurewebsites.net/api/appIdentity/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UserService userService = retrofit.create(UserService.class);
        Call<UserData> listCall = userService.getUserData(kennitala);
        listCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if(!response.isSuccessful()){
                    return;
                }
                UserData user;
                if(response.body().getImage()==null){
                    user = new UserData(
                            response.body().getName(),
                            response.body().getSurname(),
                            response.body().getBirthday(),
                            "",
                            response.body().getBio(),
                            response.body().getAverageReview());
                }else{
                    user = response.body();
                }

                Log.e("name ",""+user.getName());
                Log.e("check ",""+TextUtils.isEmpty(user.getImage()));

                /* ASSIGNMENTS */
                txtBirthday.setText(user.getBirthday());
                txtProfileDescription.setText(user.getBio());
                txtProfileName.setText(user.getName());
                txtProfileSurname.setText(user.getSurname());

                //DECODE STRING TO BITMAP
                if(!user.getImage().isEmpty()){
                    imgProfile.setImageBitmap(BitmapFactory.decodeByteArray((Base64.decode(user.getImage(), Base64.DEFAULT)),0, (Base64.decode(user.getImage(), Base64.DEFAULT).length)));
                }

                ratingBar.setRating(user.getAverageReview());
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                String message = "Profile GET Error.";
                Toast.makeText(getActivity(), message,Toast.LENGTH_LONG).show();
                Log.e("ERROR","error");
                progressBar.setVisibility(View.INVISIBLE);
            }

        });
    }
}