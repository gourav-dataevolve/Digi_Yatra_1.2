package com.example.digi_yatra_12.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.digi_yatra_12.BaseClass;
import com.example.digi_yatra_12.GlobalApplication;
import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.digi_yatra_12.roomDatabase.ConnectionDB;
import com.example.model.ConnectionDetails;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class Alomst_done extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    String aadharData;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private ConnectionDB connectionDB;
    private String myConnectionId, myDID, theirDid;
    private  JSONObject jsonObject, jsonObject1;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alomst_done);
        if (getIntent().hasExtra("aadharData")) {
            aadharData = getIntent().getStringExtra("aadharData");
        }
//        this.imageView = (ImageView)this.findViewById(R.id.imageView5);
        Button photoButton = (Button) this.findViewById(R.id.cameraBtn);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Intent intent =new Intent(Alomst_done.this, Camera_Profile.class);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] b = baos.toByteArray();
            String encImage = Base64.encodeToString(b, Base64.NO_WRAP);
            Log.d("popopopopopopopopo", encImage);
            requestCredential(encImage);

            intent.putExtra("data",photo);
           // startActivity(intent);
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
////            imageView.setImageBitmap(photo);

        }
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        // if the intentResult is null then
//        // toast a message as "cancelled"
//        if (intentResult != null) {
//            if (intentResult.getContents() == null) {
//                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//            } else {
//
//                String scan_result = intentResult.getContents();
//                Intent intent =new Intent(Alomst_done.this, Camera_Profile.class);
//                intent.putExtra("values", scan_result);
//
//                startActivity(intent);
//
//
//
//
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }


    }
    private void requestCredential(String selfie) {
        String idJsonKey = "";
        String idJsonFaceb64key = "";
        String idTypekey = "";
       /* try {
            JSONObject jsonObject = AadharDatabase.getInstance(Alomst_done.this).Dao().getAadharData(). get(0).getJson().getJSONObject("request_schema");
            idJsonKey = jsonObject.getString("idJson");
            idJsonFaceb64key = jsonObject.getString("faceb64");
            idTypekey = jsonObject.getString("idType");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        String json = "{\"comment\":\"string\",\"formats\":[{\"attach_id\":\"string\",\"format\":\"aries/ld-proof-vc-detail@v1.0\"}],\"requests~attach\":[{\"@id\":\"string\",\"mime-type\":\"application/json\",\"data\":{\"json\":{\"credential\":{\"@context\":[\"https://www.w3.org/2018/credentials/v1\",\"https://dyce-context.s3.us-west-2.amazonaws.com/v1.json\",\"https://w3id.org/security/bbs/v1\"],\"id\":\"https://digiyatafoundation.com/credentialid\",\"type\":[\"VerifiableCredential\"],\"issuer\":{\"id\":\"did:dataevolve:1234\",\"name\":\"DigiYataraFoundation\"}, \"issuanceDate\":\"2020-01-01T19:23:24Z\",\"expirationDate\":\"2100-01-01T19:23:24Z\",\"credentialSubject\":{\"id\":\"https://digiyatrafoundation.com/credentialsubjectid\",\"selfie\":\"Base64 selfie image\",\"idType\":\"aadhar\",\"idJson\":{}}},\"options\":{\"proofPurpose\":\"assertionMethod\",\"created\":\"2020-04-02T18:48:36Z\",\"proofType\":\"BbsBlsSignature2020\"}}}}]}";
        try {
            jsonObject = new JSONObject(json.trim());


            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").remove("idJson");
            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").put("idJson", aadharData);


            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").remove("selfie");
            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").put("faceb64", selfie);

            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").remove("idType");

            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").put("idType", "aadhaar"); ///TODO don,t know the value of idTypekey so putting the static value "aadhar

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("digiyatra", Context.MODE_PRIVATE);
        String connectionId = sharedPreferences.getString("connection_id","");
        JSONObject getConnectionJsonObject = BaseClass.getConnection(connectionId, GlobalApplication.agent);
        ConnectionDetails connectionDetails  = new Gson().fromJson(getConnectionJsonObject.toString().trim(), ConnectionDetails.class);
        myConnectionId = connectionDetails.getConnRecord().get(0).getConnectionID();
        myDID = connectionDetails.getConnRecord().get(0).getMyDID();
        theirDid = connectionDetails.getConnRecord().get(0).getTheirDID();
        GetConnectionData getConnectionData = new GetConnectionData();
        getConnectionData.execute();
        //ConnectionDB connectionDB = AadharDatabase.getInstance(Alomst_done.this).Dao().getConnectionData();
       // JSONObject jsonObject1 = connectionDB.getJson();
       // String type = connectionDB.getType();
       // AadharDatabase.getInstance(Alomst_done.this).Dao().updateConnection(new ConnectionDB(0,myConnectionId,type,jsonObject1,myDID,theirDid));
       // BaseClass.requestCredential(myDID, theirDid,jsonObject, GlobalApplication.agent);

    }
    class GetConnectionData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            connectionDB = AadharDatabase.getInstance(Alomst_done.this).Dao().getConnectionData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            jsonObject1 = connectionDB.getJson();
            type = connectionDB.getType();
            UpdateConnectionData updateConnectionData = new UpdateConnectionData();
            updateConnectionData.execute();
        }
    }

    class UpdateConnectionData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            AadharDatabase.getInstance(Alomst_done.this).Dao().updateConnection(new ConnectionDB(0,myConnectionId,type,jsonObject1,myDID,theirDid));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("checkJsonObject", jsonObject.toString());
            BaseClass.requestCredential(myDID, theirDid,jsonObject, GlobalApplication.agent);
        }
    }



}