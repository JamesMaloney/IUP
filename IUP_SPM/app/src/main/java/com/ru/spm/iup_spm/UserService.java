package com.ru.spm.iup_spm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("getToken/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("registerUser/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
