package pl.czak.minimal.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import pl.czak.minimal.dto.api.GetAccessTokenRequest;
import pl.czak.minimal.dto.api.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TokenManagement {
    private static final String TOKEN_PREF_NAME = "token_pref";
    private static final String TOKEN_KEY = "access_token";
    private static final String REFRESH_TOKEN_KEY = "refresh_token";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static TokenManagement instance;

    public static TokenManagement getInstance(Context context){
        if(instance != null){
            return instance;
        }
        return new TokenManagement(context);
    }

    private TokenManagement(Context context){
        sharedPreferences = context.getSharedPreferences(TOKEN_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveToken(String token, String refreshToken){
        editor.putString(TOKEN_KEY, token);
        editor.putString(REFRESH_TOKEN_KEY, refreshToken);
        editor.apply();
    }

    public String getToken(){
        String accessToken = sharedPreferences.getString(TOKEN_KEY, null);
        if(accessToken != null && isTokenExpired(accessToken)){
            String refreshToken = sharedPreferences.getString(REFRESH_TOKEN_KEY, null);
            Retrofit instance = RetrofitClient.getInstance();
            LoginApiService loginApiService = instance.create(LoginApiService.class);
            GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest(refreshToken);
            Call<LoginResponse> token = loginApiService.token(getAccessTokenRequest);
            clearToken();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            clearToken();
            token.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        LoginResponse body = response.body();
                        String newAccessToken = body.getData().getAccessToken();
                        String refreshToken = body.getData().getRefreshToken();
                        saveToken(newAccessToken, refreshToken);
                    }
                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    countDownLatch.countDown();
                }
            });
            try{
                countDownLatch.await(5, TimeUnit.SECONDS);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                clearToken();
            }
            accessToken = sharedPreferences.getString(TOKEN_KEY, null);
        }
        return accessToken;
    }

    private boolean isTokenExpired(String token){
        try{
            DecodedJWT decode = JWT.decode(token);
            return decode.getExpiresAt().getTime() < System.currentTimeMillis();
        }catch (Exception e){
            return false;
        }
    }

    public void clearToken(){
        editor.remove(TOKEN_KEY);
        editor.remove(REFRESH_TOKEN_KEY);
        editor.apply();
    }
}
