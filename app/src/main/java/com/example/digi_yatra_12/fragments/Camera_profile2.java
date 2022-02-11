package com.example.digi_yatra_12.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.StartActivity;
import com.example.digi_yatra_12.navbar.Navbar_main;
import com.example.digi_yatra_12.roomDatabase.AAdharData;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.util.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Camera_profile2 extends AppCompatActivity {
    private ImageView imgAadhar;
    private String faceB64, idType, aadharId, name;
    private TextView textAAdhar, textName, textIdType;
    private Button btnAcccept;
    private JSONObject myJson;
    private String issuerName,issuerId, type ;
    private JSONObject showedDataJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_profile2);
        initView();
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
            showedDataJson = new JSONObject();
            showedDataJson.put("givenName", name);
            showedDataJson.put("idNumber", aadharId);
            showedDataJson.put("idType", idType);
            showedDataJson.put("faceB64", faceB64);
            textAAdhar.setText(aadharId);
            textName.setText(name);
            Bitmap bm = MyUtils.StringToBitMap(faceB64);
            imgAadhar.setImageBitmap(bm);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        textAAdhar = findViewById(R.id.txt_aadhar_id);
        textName = findViewById(R.id.txt_name);
        imgAadhar = findViewById(R.id.img_aadhar);
        btnAcccept = findViewById(R.id.btn_accept);
       // textIdType = findViewById(R.id.)
        btnAcccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add fifth column to below database which is json of data which is showing in current screen.
                AadharDatabase.getInstance(Camera_profile2.this).Dao().saveAadharData(new AAdharData(0,myJson, issuerId,issuerName,type,showedDataJson));
                //List<AAdharData> aadharData = AadharDatabase.getInstance(Camera_profile2.this).Dao().getAadharData();
              //  Log.d("aadharData",aadharData.toString());
                startActivity(new Intent(Camera_profile2.this, Navbar_main.class));
                finish();
            }
        });
    }

}