package com.ru.spm.iup_spm;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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

    @POST("join/")
    Call<String> joinParty(@Body JoinParty joinParty);

    @POST("createEvent/")
    Call<EventResponse> createEvent(@Body EventRequest eventRequest);

    @GET("{kennitala}/")
    Call<UserData> getUserData(@Path("kennitala") String kennitala);

    @GET("getNearestEvents")
    Call<List<Event>> getEvents(@Query("latitude") String latitude, @Query("longitude") String longitude );

    @GET("myEvents/{kennitala}")
    Call<List<Event>> getMyEvents(@Path("kennitala") String kennitala);

    @GET("details")
    Call<Event> getParty(@Query("latitude") String latitude, @Query("longitude") String longitude);

    @GET("{kennitala}/hostname")
    Call<ResponseBody> getHostName(@Path("kennitala") String kennitala);

    @GET("{kennitala}/rating")
    Call<ResponseBody> getRatingUser(@Path("kennitala")String kennitala);

    @GET("joined/{kennitala}")
    Call<List<Event>> getEventsJoined(@Path("kennitala") String kennitala);

    @GET("/delete")
    Call<ResponseBody> deleteEvent();
}
