package com.example.digi_yatra_12.roomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.model.IssuersVerifier;

import org.json.JSONObject;

@Entity(tableName = "boarding_pass")
public class BoardingPassData {
    @PrimaryKey(autoGenerate = true)
    private int id =0;
    private String passangerName;
    private String issuerName;
    private String flightNo = "";
    private String from = "";
    private String to = "";
    private String date ;
    private String pnr = "";
    private String sequence = "";
    private String seat = "";
    private String departureTime ;

    public BoardingPassData(int id, String passangerName, String issuerName, String flightNo, String from, String to, String date, String pnr, String sequence, String seat, String departureTime) {
        this.id = id;
        this.passangerName = passangerName;
        this.issuerName = issuerName;
        this.flightNo = flightNo;
        this.from = from;
        this.to = to;
        this.date = date;
        this.pnr = pnr;
        this.sequence = sequence;
        this.seat = seat;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public String getPassangerName() {
        return passangerName;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getPnr() {
        return pnr;
    }

    public String getSequence() {
        return sequence;
    }

    public String getSeat() {
        return seat;
    }

    public String getDepartureTime() {
        return departureTime;
    }
}
