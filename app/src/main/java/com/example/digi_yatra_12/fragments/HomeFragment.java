package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.digi_yatra_12.R;

import com.example.digi_yatra_12.adapters.BoardingPassAdapter;
import com.example.digi_yatra_12.adapters.SliderAdapter;
import com.example.digi_yatra_12.databinding.FragmentHomeFragmentBinding;
import com.example.digi_yatra_12.roomDatabase.AadharDatabase;
import com.example.digi_yatra_12.roomDatabase.BoardingPassData;
import com.example.model.SliderData;
import com.smarteist.autoimageslider.SliderView;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
FragmentHomeFragmentBinding binding;
    ImageButton add;
    Button popup;
    Layout add1;
    private ViewPager viewPager;
    private SpringDotsIndicator dotsIndicator;
    private ConstraintLayout constraintNoData;
    private ConstraintLayout constraintViewPager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ImageSwitcher imageslider;

    Intent intent;
    String url2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCqZAnX28YoyZnR0KUGsq9eVAeRzBbnfibng&usqp=CAU";
    String url1 = "https://cdn03.collinson.cn/blog/2019/aug/header-airport-family-travel-vacation-5537204d-e09c-45b7-8db2-e4f2f58437b5.png?h=380&la=en&w=1280";
    String url3 = "https://cdn03.collinson.cn/blog/2019/aug/header-airport-family-travel-vacation-5537204d-e09c-45b7-8db2-e4f2f58437b5.png?h=380&la=en&w=1280";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_home_fragment, container, false );
        intent = new Intent( getActivity(), pop_crediential.class );
        initViews(rootView);
        GetBoardingPass getBoardingPass = new GetBoardingPass();
        getBoardingPass.execute();

// we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = rootView.findViewById( R.id.slider );

        // adding the urls inside array list
        sliderDataArrayList.add( new SliderData( url1 ) );
        sliderDataArrayList.add( new SliderData( url2 ) );
        sliderDataArrayList.add( new SliderData( url3 ) );

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter( HomeFragment.this, sliderDataArrayList );

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection( SliderView.LAYOUT_DIRECTION_LTR );

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter( adapter );

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec( 3 );

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle( true );

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        return rootView;
    }

    private void initViews(View rootView) {
        viewPager = rootView.findViewById(R.id.viewPager);
        dotsIndicator = (SpringDotsIndicator) rootView.findViewById(R.id.spring_dots_indicator);
        constraintNoData = rootView.findViewById(R.id.constraint_no_data);
        constraintViewPager = rootView.findViewById(R.id.constraint_view_pager);
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
//
//
        add = (ImageButton) view.findViewById( R.id.imageButton15 );

        add.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent( getActivity(), UpdateYourTravelActivity.class );
                startActivity( intent );
            }
        } );
//
//        popup = (Button) view.findViewById( R.id.ShareBtn );
//        popup.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent = new Intent( getActivity(), Pop_Share_home.class );
//                startActivity( intent );
//            }
//        } );

    }

    //dailoug box


//    @SuppressLint("ResourceType")
//    private void showStartDialog() {
//
//        final AlertDialog.Builder builder;
//
//        builder = new AlertDialog.Builder( getParentFragment().getActivity() );
////        builder .setTitle("one time Dialoug");
////              builder  .setMessage("hello");
//        builder.setView( R.layout.activity_pop_sucess_register
//        );
//
////        builder.setPositiveButton( R.id.OkBtn2, new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                dialogInterface.dismiss();
////
////            }
////        } )
//
////    builder.setView( R.id.OkBtn2 );
//
////               builder .setPositiveButton("OKBTN", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface  dialogInterface, int which) {
////                        dialogInterface.dismiss();
////                    }
////                })
//             builder.show();
////        SharedPreferences prefs = getActivity().getSharedPreferences( "prefs", Context.MODE_PRIVATE );
////        SharedPreferences.Editor editor =prefs.edit();
////        editor.putBoolean( "firstStart",true );
////        editor.apply();
//
//    }

    public class GetBoardingPass extends AsyncTask<Void, Void, Void> {
        List<BoardingPassData> boardingPassData;
        @Override
        protected Void doInBackground(Void... voids) {
            boardingPassData = AadharDatabase.getInstance(getContext()).Dao().getBoardingPass();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (boardingPassData == null || boardingPassData.isEmpty()) {
                constraintViewPager.setVisibility(View.INVISIBLE);
                constraintNoData.setVisibility(View.VISIBLE);
            }
            else {
                constraintViewPager.setVisibility(View.VISIBLE);
                constraintNoData.setVisibility(View.INVISIBLE);
                BoardingPassAdapter boardingPassAdapter = new BoardingPassAdapter(getActivity(), boardingPassData);
                viewPager.setAdapter(boardingPassAdapter);
                dotsIndicator.setViewPager(viewPager);
            }
        }
    }

}

