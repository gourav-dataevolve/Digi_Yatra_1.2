package com.example.digi_yatra_12.fragments;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.roomDatabase.AAdharData;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.util.MyUtils;

import org.json.JSONException;

import java.util.List;

public class WalletFragment2 extends Fragment {
    TextView textName;
    private ImageView photo;

    public WalletFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet2, container, false);
        initViews(view);
        getData();
        return view;
    }

    private void getData() {
        GetAadhar getAadhar  = new GetAadhar();
        getAadhar.execute();
       // List<AAdharData> aadharData = AadharDatabase.getInstance(getContext()).Dao().getAadharData();
/*
        try {
            String face64 =  aadharData.get(0).getJson().getJSONObject("credentialSubject").getString("faceB64");
            String name = aadharData.get(0).getJson().getJSONObject("credentialSubject").getString("givenName");
            Bitmap bm = MyUtils.StringToBitMap(face64);
            textName.setText(name);
            if (bm != null)
            photo.setImageBitmap(bm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
    }

    private void initViews(View view) {
        textName = view.findViewById(R.id.txt_name);
        photo = view.findViewById(R.id.img_photo);
    }
    private class GetAadhar extends AsyncTask<String, String, List<AAdharData>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected List<AAdharData> doInBackground(String... strings) {
            List<AAdharData> aadharData = AadharDatabase.getInstance(getContext()).Dao().getAadharData();
            return aadharData;
        }
        @Override
        protected void onPostExecute(List<AAdharData> aAdharData) {
            super.onPostExecute(aAdharData);
            try {
                String face64 =  aAdharData.get(0).getJson().getJSONObject("credentialSubject").getString("faceB64");
                String name = aAdharData.get(0).getJson().getJSONObject("credentialSubject").getString("givenName");
                Bitmap bm = MyUtils.StringToBitMap(face64);
                textName.setText(name);
                if (bm != null)
                    photo.setImageBitmap(bm);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}