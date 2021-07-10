package com.example.madpropertypal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.R;
import com.example.madpropertypal.models.OfferModel;
import com.example.madpropertypal.models.PropertyModel;

import java.util.List;

public class OffersRecyclerAdapter extends RecyclerView.Adapter<OffersRecyclerAdapter.myViewHolder>{



    Context context;
    List<OfferModel> offersList;


    public OffersRecyclerAdapter(Context context,List<OfferModel> TempList){

        this.offersList=TempList;
        this.context=context;

    }




    @NonNull
    @Override
    public OffersRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_layout, parent, false);
        OffersRecyclerAdapter.myViewHolder viewHolder =  new OffersRecyclerAdapter.myViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull final OffersRecyclerAdapter.myViewHolder holder, int position) {

        OfferModel offerModel = offersList.get(position);

        holder.interestTV.setText(offerModel.getInterest());
        holder.amountTV.setText("Amount: " + String.valueOf(offerModel.getOfferPrice()));
        holder.expiryTV.setText("Expires on: " + offerModel.getExpiryDate());


    }



    @Override
    public int getItemCount() {
        return offersList.size();
    }














    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView interestTV,amountTV, expiryTV;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            interestTV = itemView.findViewById(R.id.interestTV);
            amountTV = itemView.findViewById(R.id.amountTV);
            expiryTV = itemView.findViewById(R.id.expiryTV);


        }
    }

}
