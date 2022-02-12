package com.example.model;

import com.google.gson.annotations.SerializedName;

public class ValidateFaceB64Response {
    @SerializedName("Error")
    String Error;
    @SerializedName("liveness_result")
    String liveness_result;
    @SerializedName("conf")
    String conf;

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getLiveness_result() {
        return liveness_result;
    }

    public void setLiveness_result(String liveness_result) {
        this.liveness_result = liveness_result;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }
}
