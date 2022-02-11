package com.example.digi_yatra_12.fragments;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digi_yatra_12.R;

import com.example.digi_yatra_12.databinding.FragmentHomeFragmentBinding;

public class HomeFragment extends Fragment {
FragmentHomeFragmentBinding binding;
    Button add;
    Layout add1;
    WalletFragment firstfragment;
    Home_fragment2 secondFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        binding = FragmentHomeFragmentBinding.inflate(getLayoutInflater());
        return view;


    }
    public  void onDestroy(){
        super.onDestroy();
        binding=null;
    }
//    private void changeFragment(Fragment fr){
//        FrameLayout fl = (FrameLayout) findViewById(R.id.mainframe);
//        fl.removeAllViews();
//        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
//        transaction1.add(R.id.mainframe, fr);
//        transaction1.commit();
//    }
}

