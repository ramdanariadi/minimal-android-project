package pl.czak.minimal.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApiService {

    @GET("/api/v1/product")
    Call<Void> getProducts();
}
