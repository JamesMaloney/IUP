package com.ru.spm.iup_spm;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getRetrofitRegister(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofitRegister =  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://iupusesservice.azurewebsites.net/api/appIdentity/").client(okHttpClient).build();
        return retrofitRegister;
    }

    public static UserService getServiceRegister(){
        UserService userService = getRetrofitRegister().create(UserService.class);
        return userService;
    }

    //LOGIN _------___--_-_--_-_-_-___-_--_-__-_--_---_--

    public static Retrofit getRetrofitLogin(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofitlogin =  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://iupauthservice.azurewebsites.net/api/appIdentity/").client(okHttpClient).build();

        return retrofitlogin;
    }

    public static UserService getServiceLogin(){
        UserService userService = getRetrofitLogin().create(UserService.class);
        return userService;
    }

    //EVENTS _------___--_-_--_-_-_-___-_--_-__-_--_---_--
    /*TODO CHECK API*/
    public static Retrofit getRetrofitEvent(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofitCreateEvent =  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://iuppartyservice.azurewebsites.net/api/event/").client(okHttpClient).build();

        return retrofitCreateEvent;
    }
    /*TODO*/


    public static UserService getServiceEvents(){
        UserService userService = getRetrofitEvent().create(UserService.class);
        return userService;
    }
}
