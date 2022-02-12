package com.example.digi_yatra_12.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.fragments.AddDataUrlActivity;
import com.example.digi_yatra_12.fragments.Credential_Choose;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.digi_yatra_12.roomDatabase.ConnectionDB;
import com.example.model.IssuersVerifier;
import com.example.util.CustomProgressDialog;
import com.example.util.MyHandler;
import com.google.gson.Gson;

import java.util.List;

public class PopAcknowledgementDialogActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_acknowledgement);
        customProgressDialog = new CustomProgressDialog(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .91), (int) (height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 50;

        getWindow().setAttributes(params);

        Button btn= findViewById(R.id.OkBtn2);
        Button cancelButton = findViewById(R.id.btn_cancel);
        CheckBox checkBox = findViewById(R.id.checkBox2);
        linearLayout = findViewById(R.id.linearLayout);
        String connectionId = getIntent().getStringExtra("connectionId");
        GetConnectionData getConnectionData = new GetConnectionData(connectionId);
        getConnectionData.execute();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    Intent i = new Intent(PopAcknowledgementDialogActivity.this, AddDataUrlActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_enable, getResources().newTheme())));
                }
                else {
                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_disable, getResources().newTheme())));
                }
            }
        });
    }
    private class GetConnectionData extends AsyncTask<Void, Void, Void> {
        String connectionId;
        IssuersVerifier issuersVerifier;
        GetConnectionData (String connectionId) {
            this.connectionId = connectionId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            customProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ConnectionDB connectionDB = AadharDatabase.getInstance(PopAcknowledgementDialogActivity.this).Dao().getConnectionData(connectionId);
            issuersVerifier = new Gson().fromJson(connectionDB.getJson().toString(), IssuersVerifier.class);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            customProgressDialog.dismiss();
            List<String> requestField = issuersVerifier.getRequestFieldsForUser();
            for (int i=0; i<requestField.size(); i++) {
                TextView textView = new TextView(PopAcknowledgementDialogActivity.this);
                textView.setText(i+1+"."+requestField.get(i));
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                buttonLayoutParams.setMargins(0, 10, 0, 0);
                textView.setLayoutParams(buttonLayoutParams);
                linearLayout.addView(textView);
            }
        }


    }

}