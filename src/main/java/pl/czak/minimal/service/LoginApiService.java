package pl.czak.minimal.service;

import pl.czak.minimal.dto.api.LoginRequest;
import pl.czak.minimal.dto.api.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApiService {
    @POST("/api/v1/user/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
