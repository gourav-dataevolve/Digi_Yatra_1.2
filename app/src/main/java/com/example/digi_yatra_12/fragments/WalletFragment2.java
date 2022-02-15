package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.adapters.CardAdapter;
import com.example.digi_yatra_12.roomDatabase.AAdharData;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.model.IssuersVerifier;
import com.example.util.MyUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class WalletFragment2 extends Fragment implements CardAdapter.CardClick {
    private RecyclerView recyclerview;
    private List<AAdharData> aAdharDataList = new ArrayList<>();
    private CardAdapter cardAdapter;
    Button add;

    public WalletFragment2() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet2, container, false);
        initViews(view);
        getData();



        add = (Button) view.findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Fragment secondfragment=new Home_fragment2();
//                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.activity_main_nav_host_fragment,secondfragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                Intent intent =new Intent(getActivity(),Credential_Choose.class);
                startActivity(intent);


            }
        });
        return view;
    }

    private void getData() {
        GetAadhar getAadhar  = new GetAadhar();
        getAadhar.execute();
    }

    private void initViews(View view) {
       recyclerview = view.findViewById(R.id.recycler);

    }


    @Override
    public void onCardClick(AAdharData aAdharData, View view) {

    }

    private class GetAadhar extends AsyncTask<String, String, List<AAdharData>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected List<AAdharData> doInBackground(String... strings) {

            aAdharDataList = AadharDatabase.getInstance(getContext()).Dao().getAadharData();
            return aAdharDataList;
        }
        @Override
        protected void onPostExecute(List<AAdharData> aAdharDataList) {
            super.onPostExecute(aAdharDataList);
            if (aAdharDataList != null && !aAdharDataList.isEmpty()) {
                cardAdapter = new CardAdapter(getContext(), aAdharDataList, new CardAdapter.CardClick() {
                    @Override
                    public void onCardClick(AAdharData aAdharData, View view) {

                    }
                });
                recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerview.setAdapter(cardAdapter);
                cardAdapter.notifyDataSetChanged();
            }
            else {

            }
        }
    }
}