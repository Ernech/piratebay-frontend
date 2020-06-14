package com.example.piratebayfrontend.Clases;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;

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

    public static JSONArray getFeaturesFromJWT(String JWTEncoded) throws Exception {
        JSONArray features = new JSONArray();
        try {
            String[] split = JWTEncoded.split("\\.");
            JSONObject body = new JSONObject(getJson(split[1]));
            features =  body.getJSONArray("features");
        } catch (UnsupportedEncodingException e) {
            //Error
        }
        return features;
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
