package com.example.piratebayfrontend.Clases;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;

import java.util.Map;

public class VerifyFeatures {

    public static boolean hasFeature(Context context, String feature){
        Map<String, String> tokens;
        tokens = TokensControl.retrieveTokens(context);
        boolean hasfeature = false;

        try{
            JSONArray features = JWTUtils.getFeaturesFromJWT(tokens.get("authentication"));
            for(int i=0; i<features.length(); i++){
                if (feature.equals(features.get(i))){
                    hasfeature = true;

                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
        }catch (Exception e){
            Log.e("Error",e+"");
        }

        return hasfeature;
    }

}
