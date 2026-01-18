package pl.czak.minimal.service;

import retrofit2.Call;
import retrofit2.http.POST;

public interface TransactionApiService {
    @POST("/api/v1/transaction")
    Call<Void> order();
}
