package pl.czak.minimal.service;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManagement {
    private static final String TOKEN_PREF_NAME = "token_pref";
    private static final String TOKEN_KEY = "access_token";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public TokenManagement(Context context){
        sharedPreferences = context.getSharedPreferences(TOKEN_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveToken(String token){
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public void clearToken(){
        editor.remove(TOKEN_KEY);
        editor.apply();
    }
}
