package pl.czak.minimal.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApiService {
    @GET("/api/v1/user")
    Call<Void> getProfile();
}
