package pl.czak.minimal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.czak.minimal.dto.api.LoginRequest;
import pl.czak.minimal.dto.api.LoginResponse;
import pl.czak.minimal.service.LoginApiService;
import pl.czak.minimal.service.RetrofitClient;
import pl.czak.minimal.service.TokenManagement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);
        EditText username = findViewById(R.id.login_username);
        EditText password = findViewById(R.id.login_password);
        loginButton.setOnClickListener(view -> {
            login(username.getText().toString(), password.getText().toString());
        });
    }

    public void login(String username, String password){
        TokenManagement tokenManagement = TokenManagement.getInstance(this);
        Retrofit instance = RetrofitClient.getInstance();
        LoginApiService loginApiService = instance.create(LoginApiService.class);

        LoginRequest loginRequest = new LoginRequest(username, password);
        Call<LoginResponse> login = loginApiService.login(loginRequest);

        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if( response.isSuccessful()){
                    LoginResponse body = response.body();
                    tokenManagement.saveToken(body.getData().getAccessToken(), body.getData().getRefreshToken());
                    Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "login failure" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "login failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
