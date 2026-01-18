package pl.czak.minimal.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApiService {
    @GET("/api/v1/category")
    Call<Void> getCategories();
}
