package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.navbar.NavbarMainActivity;
import com.example.digi_yatra_12.roomDatabase.AAdharData;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.model.IssuersVerifier;
import com.example.util.MyUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Camera_profile2 extends AppCompatActivity {
    private ImageView imgAadhar;
    private String faceB64, idType, aadharId, name;
    private TextView textAAdhar, textName, textIdType;
    private Button btnAcccept;
    private JSONObject myJson;
    private String issuerName,issuerId, type ;
    private IssuersVerifier issuersVerifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_profile2);
        initView();
        String issuerVerifierString = getIntent().getStringExtra("issuersVerifier");
        issuersVerifier = new Gson().fromJson(issuerVerifierString, IssuersVerifier.class);
        String jsonString = getIntent().getStringExtra("json");
        try {
            JSONObject json = new JSONObject(jsonString);
            myJson = json.getJSONObject("message").getJSONObject("Message").getJSONArray("credentials~attach").getJSONObject(0)
                    .getJSONObject("data").getJSONObject("json");
            issuerName = myJson.getJSONObject("issuer").getString("name");
            issuerId = myJson.getJSONObject("issuer").getString("id");
            type = myJson.getString("type");
            name = myJson.getJSONObject("credentialSubject").getString("fullName");
            aadharId = myJson.getJSONObject("credentialSubject").getString("idNumber");
            idType = myJson.getJSONObject("credentialSubject").getString("idType");
            faceB64 = myJson.getJSONObject("credentialSubject").getString("faceB64");
            textAAdhar.setText(aadharId);
            textName.setText(name);
            Bitmap bm = MyUtils.StringToBitMap(faceB64);
            imgAadhar.setImageBitmap(bm);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ImageButton ib = (ImageButton)findViewById(R.id.backBtn1);
        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        textAAdhar = findViewById(R.id.txt_aadhar_id);
        textName = findViewById(R.id.full_name);
        imgAadhar = findViewById(R.id.img_aadhar);
        btnAcccept = findViewById(R.id.btn_accept);
       // textIdType = findViewById(R.id.)
        btnAcccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AadharDatabase.getInstance(Camera_profile2.this).Dao().saveAadharData(new AAdharData(0,myJson, issuerId,issuerName,type,issuersVerifier));
                startActivity(new Intent(Camera_profile2.this, NavbarMainActivity.class));
                finish();
            }
        });
    }

}