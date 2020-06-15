package com.example.piratebayfrontend.Clases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class TokensControl {

    // Clase creada para manejar el token de Authorization y Refresh.

    public static void saveTokens (Object authnToken, Object refreshToken, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AuthnToken", authnToken.toString());
        editor.putString("RefreshToken", refreshToken.toString());
        editor.commit();
    }

    public static Map<String, String> retrieveTokens (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String authnToken = sharedPreferences.getString("AuthnToken","");
        String refreshToken = sharedPreferences.getString("RefreshToken","");
        Map<String, String> tokens = new HashMap<>();
        tokens.put("authentication", authnToken);
        tokens.put("refresh", refreshToken);
        return tokens;
    }

    public static void removeTokens (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
