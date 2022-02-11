package com.example.digi_yatra_12.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digi_yatra_12.R;
import com.example.model.IssuersVerifier;

import java.util.List;

public class IssuerAdapter extends RecyclerView.Adapter<IssuerAdapter.IssuerHolder>{
    Context context;
    List<IssuersVerifier> issuersVerifierList;
    IssuerClick issuerClick;
    public IssuerAdapter(Context context, List<IssuersVerifier> issuersVerifierList, IssuerClick issuerClick) {
        this.context = context;
        this.issuersVerifierList = issuersVerifierList;
        this.issuerClick = issuerClick;
    }

    @NonNull
    @Override
    public IssuerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_issuer, parent, false);
        IssuerHolder issuerHolder = new IssuerHolder(listItem);
        return issuerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IssuerHolder holder, int position) {
       // holder.issuerLogo.setImageBitmap(MyUtils.StringToBitMap(issuersVerifierList.get(position).));
        holder.issuerName.setText(issuersVerifierList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return issuersVerifierList.size();
    }

    public class IssuerHolder extends RecyclerView.ViewHolder{
        ImageView issuerLogo, next;
        TextView issuerName;
        ImageButton popIssuerButton;
        public IssuerHolder(@NonNull View itemView) {
            super(itemView);
            issuerName = itemView.findViewById(R.id.txt_issuer_name);
            issuerLogo = itemView.findViewById(R.id.img_issuer_logo);
            next = itemView.findViewById(R.id.img_next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    issuerClick.onIssuerClick(issuersVerifierList.get(getAdapterPosition()), itemView);
                }
            });
        }
    }
    public interface IssuerClick{
        void onIssuerClick(IssuersVerifier issuersVerifier, View view);
    }
}
