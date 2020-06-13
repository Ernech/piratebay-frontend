package com.example.piratebayfrontend.Clases;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class JWTUtils {
    public static void decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            Log.d("JWT_DECODED", "Signature: " + getJson(split[2]));
        } catch (UnsupportedEncodingException e) {
            //Error
        }
    }
    public static void getFeaturesFromJWT(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            JSONObject arrayFeatures= new JSONObject(getJson(split[1]));
            Object features=  arrayFeatures.get("features");
            Log.d("FEATURES", "Features: " + features);

        } catch (UnsupportedEncodingException e) {
            //Error
        }
    }
    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
