package com.example.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.digi_yatra_12.activities.PopAcknowledgementDialogActivity;
import com.example.digi_yatra_12.activities.ReviewHealthCredentialsActivity;
import com.example.digi_yatra_12.fragments.Camera_profile2;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.digi_yatra_12.roomDatabase.ConnectionDB;
import com.google.gson.JsonObject;

import org.hyperledger.aries.api.Handler;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class MyHandler implements Handler {
    // Push notification implementation
    String lastTopic, lastMessage;
    Context context;

    public MyHandler(Context context) {
        this.context = context;
    }

    public String getLastNotification() {
        return lastTopic + "\n" + lastMessage;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("LongLogTag")
    @Override
    public void handle(@NonNull String topic, byte[] message) {
        lastTopic = topic;
        lastMessage = new String(message, StandardCharsets.UTF_8);

        Log.d("received notification topic: ", lastTopic);
        Log.d("received notification message: ", lastMessage);
        if (topic.equals("didexchange_states")) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(lastMessage);
                String type = jsonObject.getJSONObject("message").getJSONObject("Message").getString("@type");
                if (type.equals("https://didcomm.org/didexchange/1.0/response")) {
                    String type1 = jsonObject.getJSONObject("message").getString("Type");
                    String stateID = jsonObject.getJSONObject("message").getString("StateID");
                    if (type1.equals("post_state") && stateID.equals("completed")) {
                        String connectionId = jsonObject.getJSONObject("message").getJSONObject("Properties").getString("connectionID");
                        GetConnectionData getConnectionData = new GetConnectionData(connectionId);
                        getConnectionData.execute();
                    }
                }
/*
                if (type.equals("https://didcomm.org/issue-credential/2.0/offer-credential")) {;
                    ///if true then extact connectionID
                   */
/* {
                        "id":"dac1d4b1-cb1f-4e95-8e01-c7312622890c",
                            "topic":"didexchange_states",
                            "message":{
                        "ProtocolName":"didexchange",
                                "Type":"post_state", ///check for this type   &&condition1
                                "StateID":"completed",   ////check for stateID  &&condition2
                                "Message":{
                            "@id":"afef3cd5-78e1-49b5-ae27-386ad3e8a6cf",
                                    "@type":"https://didcomm.org/didexchange/1.0/response",   ///check for @type &&condition3
                                    "did":"did:peer:1zQmYSFZSz15hz3puTjLnbdLc8pACnUFnDrT5bVQE9UVNmkf",
                                    "did_doc~attach":{
                                "data":{
                                    "base64":"eyJAY29udGV4dCI6WyJodHRwczovL3d3dy53My5vcmcvbnMvZGlkL3YxIl0sImlkIjoiZGlkOnBlZXI6MXpRbVlTRlpTejE1aHozcHVUakxuYmRMYzhwQUNuVUZuRHJUNWJWUUU5VVZObWtmIiwidmVyaWZpY2F0aW9uTWV0aG9kIjpbeyJjb250cm9sbGVyIjoiIiwiaWQiOiIja2V5LTEiLCJwdWJsaWNLZXlCYXNlNTgiOiJBSHphb0VGWmJaVDhCSlI0bTl2OE1jUXR3QkNtdFYxZFpoVExnMXZtQW4zZCIsInR5cGUiOiJFZDI1NTE5VmVyaWZpY2F0aW9uS2V5MjAxOCJ9LHsiY29udHJvbGxlciI6IiIsImlkIjoiI2tleS0yIiwicHVibGljS2V5QmFzZTU4IjoiN21CWmtteG9kZUdFeHU2cE50V0JqWlZNYmI3ZlQ1eHVpNWF3VEh4cWdFWUYiLCJ0eXBlIjoiWDI1NTE5S2V5QWdyZWVtZW50S2V5MjAxOSJ9XSwic2VydmljZSI6W3siaWQiOiI1MDYxZTA2Ny1mYzQwLTQ1MmYtOGRiZS05N2ZhYjg1ZTM0MjciLCJwcmlvcml0eSI6MCwicmVjaXBpZW50S2V5cyI6WyJkaWQ6a2V5Ono2TWtva0ZkUFVWenc2d2JIb0ZtU2lzeUNoeHRra1VkSk5GekZpTkdXSHRuNXpxMSJdLCJzZXJ2aWNlRW5kcG9pbnQiOiJ3czovL2FiNmIxZjQ3YzY1M2E0YWQ5YmUyMzUyMTE3MjZkZjE5LTIyMjcxOTQ5My5hcC1zb3V0aC0xLmVsYi5hbWF6b25hd3MuY29tOjEwMDkyIiwidHlwZSI6ImRpZC1jb21tdW5pY2F0aW9uIn1dLCJhdXRoZW50aWNhdGlvbiI6WyIja2V5LTEiXSwiYXNzZXJ0aW9uTWV0aG9kIjpbIiNrZXktMSJdLCJrZXlBZ3JlZW1lbnQiOlsiI2tleS0yIl0sImNyZWF0ZWQiOiIyMDIyLTAyLTA3VDA3OjUyOjU5LjUwOTk4OTgzMloiLCJ1cGRhdGVkIjoiMjAyMi0wMi0wN1QwNzo1Mjo1OS41MDk5ODk4MzJaIn0="
                                },
                                "lastmod_time":"0001-01-01T00:00:00Z",
                                        "mime-type":"application/json"
                            },
                            "~thread":{
                                "pthid":"aa6e1d85-7eab-4825-8b3b-7989a9922304",
                                        "thid":"aa6e1d85-7eab-4825-8b3b-7989a9922304"
                            }
                        },
                        "Properties":{
                            "connectionID":"b4e2dd8b-ee4e-4159-8263-7a6fab9b8446",   //if true use this connectionID pass it two shared preference to next intence and prform the flow of screen no 13 flow which is already done
                                    "invitationID":"aa6e1d85-7eab-4825-8b3b-7989a9922304"
                        }
                    }*//*

                    }
*/

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if (topic.equals("issue-credential_actions")) {
            try {
                JSONObject jsonObject = new JSONObject(lastMessage);
                String type = jsonObject.getJSONObject("message").getJSONObject("Message").getString("@type");
                if (type.equals("https://didcomm.org/issue-credential/2.0/offer-credential")) {
                } else if (type.equals("https://didcomm.org/issue-credential/2.0/issue-credential")) {
                    String myDid = jsonObject.getJSONObject("message").getJSONObject("Properties").getString("myDID");
                    //fetch information from database using myDID from connection database and fetch field "responseFiledsForUser" and show this field in screen no 55
                    GetConnectionDataByMyDid getConnectionDataByMyDid = new GetConnectionDataByMyDid(myDid);
                    getConnectionDataByMyDid.execute();

                    //show screen no 19
                   /* { show this data in screen number 19


                        "id":"e5d05ef4-9090-414f-8659-1d9235ee1127",
                            "topic":"issue-credential_actions",
                            "message":{
                        "ProtocolName":"issue-credential",
                                "Message":{
                            "@id":"4461660a-49c8-4c42-94df-742c7b181b84",
                                    "@type":"https://didcomm.org/issue-credential/2.0/issue-credential",
                                    "credentials~attach":[
                            {
                                "data":{
                                "json":{  store it room dtabase first column,
                                    "@activity":[
                                    "https://www.w3.org/2018/credentials/v1",
                                            "https://dyce-activity.s3.us-west-2.amazonaws.com/v1.json",
                                            "https://w3id.org/security/bbs/v1"
                     ],
                                    "credentialSubject":{
                                        "faceB64": "",                                              //content to show in screen 20
                                                "givenName":"Vemula Gourav",                        //key and value both are dynamic in screen 20
                                                "id":"https://digiyatrafoundation.com/credentialid",  //exclude it
                                                "idNumber":"xxxxxxxx4234", //aadhar number
                                                "idType":"aadhar"                                    //TODO recieve vaccinationStatus and fullName for health credentials flow //show these values in screen 66. and after clicking accept button save data in database as we done before
                                    },
                                    "expirationDate":"2100-01-01T19:23:24Z",
                                            "id":"https://digiyatrafoundation.com/credentialid",
                                            "issuanceDate":"2022-02-07T08:43:24Z",
                                            "issuer":{
                                        "id":"did:dataevolve:EiCgQLVtTs-0LuZ8lkMj3ZrJmx7u8H2MYqsLWPotMTbJBA", //second is issuerDID
                                                "name":" DigiYataraFoundation"                              //third column of roomdatabase issuerNAme
                                    },
                                    "proof":{
                                        "created":"2022-02-07T08:43:26.370042705Z",
                                                "proofPurpose":"assertionMethod",
                                                "proofValue":"iqtVcSn6KWWD09a0hYI_5oeDHjAjKjifDjcsxfSvZIkmX7Qwa6VZq2P7Qaf9fcoAD_t9O6KrVHWAtx4DmDX_o9v1ulFgdorKvdPBkFT7U0dJFhNsmclZTA7W7FldR8c8CbFN9FFubz6dkWvGpr4ffw",
                                                "type":"BbsBlsSignature2020",
                                                "verificationMethod":"did:dataevolve:EiCgQLVtTs-0LuZ8lkMj3ZrJmx7u8H2MYqsLWPotMTbJBA#r6wEVzYf6S0MC4X-Dcc6pTuvEtwcF9CfbcFqbMZmTl0"
                                    },
                                    "type":"VerifiableCredential"                       //fourth column of roomdatabase
                                }
                            }
                            }
         ],
                            "~thread":{
                                "thid":"20709086-6970-4a53-aa30-4285b184729e"
                            }
                        },
                        "Properties":{
                            "myDID":"did:peer:1zQmd5s1XgfnB5Thop8R6UTBMKzKb9TXJiuugC4a1AXUSYVY",
                                    "piid":"20709086-6970-4a53-aa30-4285b184729e",
                                    "theirDID":"did:peer:1zQmYSFZSz15hz3puTjLnbdLc8pACnUFnDrT5bVQE9UVNmkf" //call datbase funtion connection database query parameter is myDID and theierDID, from the response of database we get response fields from user which will show in screen 20 as keys of values like full name , gender

                        }
                    }
                    }*/
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    private class GetConnectionData extends AsyncTask<Void, Void, Void> {
        String connectionId;
        ConnectionDB connectionDB;
        String issuersVerifier;
        Intent intent;

        GetConnectionData(String connectionId) {
            this.connectionId = connectionId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            connectionDB = AadharDatabase.getInstance(context).Dao().getConnectionData(connectionId);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (connectionDB != null) {
                /*SharedPreferences sharedPreferences = context.getSharedPreferences("digiyatra", context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("connection_id", connectionId);
                myEdit.apply();
                myEdit.commit();*/
                issuersVerifier = connectionDB.getJson().toString();
                intent = new Intent(context, PopAcknowledgementDialogActivity.class);
                intent.putExtra("connectionId", connectionId);
                intent.putExtra("issuer_verifier", issuersVerifier);
                context.startActivity(intent);
            }

        }
    }
       private class GetConnectionDataByMyDid extends AsyncTask<Void, Void, Void> {
        String myDid;
        ConnectionDB connectionDB;
        String issuersVerifier;
        Intent intent;

        GetConnectionDataByMyDid(String myDid) {
            this.myDid = myDid;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            connectionDB = AadharDatabase.getInstance(context).Dao().getConnectionDataByMyDid(myDid);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (connectionDB != null) {
                String vaccinationStatus = null;
                try {
                    JSONObject jsonObject = new JSONObject(lastMessage);
                    vaccinationStatus = jsonObject.getJSONObject("message").getJSONObject("Message").getJSONArray("credentials~attach").getJSONObject(0)
                            .getJSONObject("data").getJSONObject("json").getJSONObject("credentialSubject").getString("vaccinationStatus");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (vaccinationStatus == null) {
                    intent = new Intent(context, Camera_profile2.class);
                    intent.putExtra("json", lastMessage);
                    issuersVerifier = connectionDB.getJson().toString();
                    intent.putExtra("issuersVerifier", issuersVerifier);
                    context.startActivity(intent);
                }
                else {
                    intent = new Intent(context, ReviewHealthCredentialsActivity.class);
                    intent.putExtra("json", lastMessage);
                    issuersVerifier = connectionDB.getJson().toString();
                    intent.putExtra("issuersVerifier", issuersVerifier);
                    context.startActivity(intent);
                }
            }
            else {
                Toast.makeText(context, "connection not found", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
