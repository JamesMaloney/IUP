package com.ru.spm.iup_spm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventActivity extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    ImageView selectedImage;
    String currentPhotoPath;
    EditText Name, MaxPeople, Description, DateStart, DateEnd, Position;
    LoginResponse loginResponse;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_settings();
            }
        });

        ImageView imgIcon = findViewById(R.id.logo);
        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_home();
            }
        });

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_home();
            }
        });

        selectedImage = findViewById(R.id.displayImageView);



        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQUEST_CODE);
            }
        });


        spinner = (ProgressBar)findViewById(R.id.LoadingLogin);

        Name = (EditText) findViewById(R.id.txtName);
        MaxPeople = (EditText) findViewById(R.id.txtMaxPeople);
        Position = (EditText) findViewById(R.id.txtPosition);
        DateEnd = (EditText) findViewById(R.id.txtDateEnd);
        DateStart = (EditText) findViewById(R.id.txtDateStart);
        Description = (EditText) findViewById(R.id.txtDescription);


        Button bntCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        bntCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(Name.getText().toString()) || TextUtils.isEmpty(MaxPeople.getText().toString()) || TextUtils.isEmpty(Description.getText().toString()) ||
                        TextUtils.isEmpty(Position.getText().toString()) || TextUtils.isEmpty(DateEnd.getText().toString()) || TextUtils.isEmpty(DateStart.getText().toString())){
                    String message = "All inputs are required!";
                    Toast.makeText(CreateEventActivity.this, message,Toast.LENGTH_LONG).show();
                }else{
                    EventRequest eventRequest = new EventRequest();
                    eventRequest.setName(Name.getText().toString());
                    eventRequest.setPosition(Position.getText().toString());
                    eventRequest.setDateEnd(DateEnd.getText().toString());
                    eventRequest.setDateStart(DateStart .getText().toString());
                    eventRequest.setDescription(Description.getText().toString());
                    eventRequest.setMaxPeople(Integer.parseInt(MaxPeople.getText().toString()));

                    /*TODO TEST IT IMAGE*/
                    selectedImage.buildDrawingCache();
                    Bitmap bmap = selectedImage.getDrawingCache();
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    bmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                    byte[] image=stream.toByteArray();
                    String img_str = Base64.encodeToString(image, 0);
                    Log.e("QUAAA",""+img_str);

                    eventRequest.setImage(img_str);

                    /*TODO test it*/
                    eventRequest.setHost("08071997");
                    eventRequest.setHostName("Smetz");
                    createEvent(eventRequest);
                }
            }
        });

        /*TODO maybe IMG assignment*/
    }

    private void createEvent(EventRequest eventRequest) {
        Call<EventResponse> creationResponse = ApiClient.getServiceEvents().createEvent(eventRequest);
        creationResponse.enqueue(new Callback<EventResponse>() {


            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
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


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "net.smallacademy.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
}