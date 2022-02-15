package com.example.digi_yatra_12.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.roomDatabase.BoardingPassData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardingPassAdapter extends PagerAdapter {
    Context context;
    List<BoardingPassData> boardingPassDataList;
    LayoutInflater mLayoutInflater;

    public BoardingPassAdapter(Context context, List<BoardingPassData> boardingPassDataList) {
        this.context = context;
        this.boardingPassDataList = boardingPassDataList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return boardingPassDataList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((CardView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_boarding_pass, container, false);
        TextView to = itemView.findViewById(R.id.txt_to);
        to.setText(boardingPassDataList.get(position).getTo());
        TextView from = itemView.findViewById(R.id.txt_from);
        from.setText(boardingPassDataList.get(position).getFrom());
        TextView toTime = itemView.findViewById(R.id.time_to);
       // toTime.setText(boardingPassDataList.get(position).get);
        TextView fromTime = itemView.findViewById(R.id.time_from);
     //   fromTime.setText(boardingPassDataList.get(position).getTo());
        TextView issuerName = itemView.findViewById(R.id.issuer_name);
        issuerName.setText(boardingPassDataList.get(position).getIssuerName());
        TextView date = itemView.findViewById(R.id.flight_date);
        date.setText(boardingPassDataList.get(position).getDate());
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

