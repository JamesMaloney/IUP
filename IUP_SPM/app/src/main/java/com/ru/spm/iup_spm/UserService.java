package com.ru.spm.iup_spm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @POST("getToken/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("registerUser/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("createEvent/")
    Call<EventResponse> createEvent(@Body EventRequest eventRequest);

    @GET("/{kennitala}/")
    Call<UserData> listRepos(@Path("kennitala") String kennitala);

    @GET("getNearestEvents")
    Call<List<Event>> getEvents(@Query("latitude") String latitude, @Query("longitude") String longitude );

    @GET("getAllEvents/")
    Call<List<EventTry>> getAll();
}
