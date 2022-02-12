package com.example.digi_yatra_12.roomDatabase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity(tableName = "aadhar_data")
public class AAdharData {
    @PrimaryKey(autoGenerate = true)
    private int id =0;
    private JSONObject json;
    private String issuerDID = "";
    private String issuerName = "";
    private String type = "";
    private JSONObject previousShowedData ;

    public AAdharData(int id, JSONObject json, String issuerDID, String issuerName, String type, JSONObject previousShowedData) {
        this.id = id;
        this.json = json;
        this.issuerDID = issuerDID;
        this.issuerName = issuerName;
        this.type = type;      //TODO credentialType":"IdentityCredential",
        this.previousShowedData = previousShowedData;   //TODO responseFiledsForUser from the connectionDB
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public String getIssuerDID() {
        return issuerDID;
    }

    public void setIssuerDID(String issuerDID) {
        this.issuerDID = issuerDID;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getPreviousShowedData() {
        return previousShowedData;
    }

    public void setPreviousShowedData(JSONObject previousShowedData) {
        this.previousShowedData = previousShowedData;
    }
}
