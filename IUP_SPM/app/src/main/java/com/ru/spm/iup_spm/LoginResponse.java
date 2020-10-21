package com.ru.spm.iup_spm;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String kennitala;
    private String token;
    private  String name;

    public static void setAccessToken(@NonNull Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCESSTOKEN", token);
        editor.apply();
    }

    public static String getAccessToken(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        return sharedPreferences.getString("ACCESSTOKEN", null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKennitala() {
        return kennitala;
    }

    public void setKennitala(String kennitala) {
        this.kennitala = kennitala;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
