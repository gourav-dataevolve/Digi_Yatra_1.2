package com.example.digi_yatra_12.roomDatabase;

import android.util.Log;

import com.example.model.IssuersVerifier;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class TypeConverter {

    @androidx.room.TypeConverter
    public String fromResToString(JSONObject jsonObject) {
        Log.d("fromResToString", jsonObject.toString());
        return jsonObject.toString();
    }
    @androidx.room.TypeConverter
    public JSONObject  fromStringToRes(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    @androidx.room.TypeConverter
    public IssuersVerifier fromIssuerVerifierToRes(String issuersVerifierString) {
        return new Gson().fromJson(issuersVerifierString, IssuersVerifier.class);
    }
    @androidx.room.TypeConverter
    public String fromIssuersVerifierToString(IssuersVerifier issuersVerifier) {
        return new Gson().toJson(issuersVerifier);
    }
}
