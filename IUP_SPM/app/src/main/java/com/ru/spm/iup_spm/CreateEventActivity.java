package com.ru.spm.iup_spm;


import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;


public class CreateEventActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    private ImageView selectedImage;
    private EditText Name, MaxPeople, Description, DateStart, DateEnd;
    private ProgressBar spinner;
    private TextView showLocation;
    private String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        /*LOCATION*/
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        showLocation = findViewById(R.id.txtPosition);
        ImageView btnGetLocation = findViewById(R.id.btnPosition);
        spinner = (ProgressBar)findViewById(R.id.LoadingLogin);
        Name = (EditText) findViewById(R.id.txtName);
        MaxPeople = (EditText) findViewById(R.id.txtMaxPeople);
        DateEnd = (EditText) findViewById(R.id.txtDateEnd);
        DateStart = (EditText) findViewById(R.id.txtDateStart);
        Description = (EditText) findViewById(R.id.txtDescription);
        selectedImage = findViewById(R.id.displayImageView);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        ImageView imgIcon = findViewById(R.id.logo);
        Button bntCreateEvent = (Button) findViewById(R.id.btnCreateEvent);

        if(ContextCompat.checkSelfPermission(CreateEventActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CreateEventActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_settings();
            }
        });

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_home();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_home();
            }
        });

        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQUEST_CODE);
            }
        });

        bntCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(Name.getText().toString()) || TextUtils.isEmpty(MaxPeople.getText().toString()) || TextUtils.isEmpty(Description.getText().toString()) ||
                        TextUtils.isEmpty(showLocation.getText().toString()) || TextUtils.isEmpty(DateEnd.getText().toString()) || TextUtils.isEmpty(DateStart.getText().toString())){
                    String message = "All inputs are required!";
                    Toast.makeText(CreateEventActivity.this, message,Toast.LENGTH_LONG).show();
                }else{
                    EventRequest eventRequest = new EventRequest();
                    eventRequest.setName(Name.getText().toString());
                    eventRequest.setDateEnd(DateEnd.getText().toString());
                    eventRequest.setDateStart(DateStart.getText().toString());
                    eventRequest.setDescription(Description.getText().toString());
                    eventRequest.setMaxPeople(Integer.parseInt(MaxPeople.getText().toString()));

                    /*IMAGE CONVERTION*/
                    selectedImage.buildDrawingCache();
                    Bitmap bmap = selectedImage.getDrawingCache();
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    bmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                    byte[] image=stream.toByteArray();
                    String img_str = Base64.encodeToString(image, 0);
                    /*SET IMAGE*/
                    eventRequest.setImage(img_str);
                    /*GET SHARED PREFERENCES*/
                    SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                    String hostname = preferences.getString("hostname","");
                    eventRequest.setHostName(hostname);
                    eventRequest.setLatitude(Double.parseDouble(preferences.getString("latitude","")));
                    eventRequest.setLongitude(Double.parseDouble(preferences.getString("longitude","")));
                    eventRequest.setHost(preferences.getString("kennitala",""));

                    createEvent(eventRequest);
                }
            }
        });
    }

    private void createEvent(EventRequest eventRequest) {
        Call<EventResponse> creationResponse = ApiClient.getServiceEvents().createEvent(eventRequest);
        creationResponse.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, retrofit2.Response<EventResponse> response) {
                spinner.setVisibility(View.INVISIBLE);
                if(response.isSuccessful()){
                    String message = "Successful. Event Created";
                    Toast.makeText(CreateEventActivity.this, message,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateEventActivity.this,Home.class));
                    finish();
                }else{
                    String message = "An error occurred please try later.";
                    Toast.makeText(CreateEventActivity.this, message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                spinner.setVisibility(View.INVISIBLE);
                /*String message = "Error, please retry";
                Toast.makeText(CreateEventActivity.this, message,Toast.LENGTH_LONG).show();*/
                String message = "Successful. Event Created";
                Toast.makeText(CreateEventActivity.this, message,Toast.LENGTH_LONG).show();
                startActivity(new Intent(CreateEventActivity.this,Home.class));
                finish();
            }
        });
    }

    private void open_home() {
        startActivity(new Intent(this, Home.class));
    }

    private void open_settings() {
        startActivity(new Intent( this,SettingsActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
                selectedImage.setImageURI(Uri.fromFile(f));
                Log.d("tag", "ABsolute Url of Image is " + Uri.fromFile(f));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            }
        }
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery Image Uri:  " + imageFileName);
                selectedImage.setImageURI(contentUri);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    @SuppressLint("SetTextI18n")
    public void getLocation(){
        GpsTracker gpsTracker = new GpsTracker(CreateEventActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            SharedPreferences preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
            preferences.edit().putString("latitude", String.valueOf(latitude)).apply();
            preferences.edit().putString("longitude", String.valueOf(longitude)).apply();

            showLocation.setText("Position Acquired!");
        }else{
            gpsTracker.showSettingsAlert();
        }
    }
}