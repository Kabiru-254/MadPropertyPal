package com.example.madpropertypal.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.HomeActivity;
import com.example.madpropertypal.R;
import com.example.madpropertypal.SelectedPropertyDetails;
import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.sqlite.SQLiteHelper;

import java.util.List;

public class EditorsRecyclerViewAdapter extends RecyclerView.Adapter<EditorsRecyclerViewAdapter.myViewHolder>{

    Context context;
    List<PropertyModel> AvailableHouses;


    public EditorsRecyclerViewAdapter(Context context, List< PropertyModel> TempList){

        this.AvailableHouses=TempList;
        this.context=context;

    }








    @NonNull
    @Override
    public EditorsRecyclerViewAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_houses_recycler_layout, parent, false);
        EditorsRecyclerViewAdapter.myViewHolder viewHolder =  new EditorsRecyclerViewAdapter.myViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {

        PropertyModel myPropertyItems = AvailableHouses.get(position);

        String name = myPropertyItems.getPropertyName();
        String location = myPropertyItems.getLocation();
        int price = myPropertyItems.getAskingPrice();


        holder.nameTV.setText(name);
        holder.locationTV.setText(location);
        holder.priceTV.setText(String.valueOf(price));

        holder.removeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteHelper sqLiteHelpar = new SQLiteHelper(context, "HOUSES_AVAILABLE");
                Boolean removedItem = sqLiteHelpar.removeProperty(myPropertyItems);

                if (removedItem){

                    Toast.makeText(context, "something Went wrong. Try Again", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                    holder.itemView.getContext().startActivity(new Intent(context, HomeActivity.class));


                }
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SelectedPropertyDetails.class);
                intent.putExtra("selectedProperty", myPropertyItems);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);

            }
        });


    }





    @Override
    public int getItemCount() {
        return AvailableHouses.size();
    }




    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView nameTV,priceTV,locationTV, editTV, removeTV;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            nameTV = itemView.findViewById(R.id.recyclerNameTV);
            priceTV = itemView.findViewById(R.id.recyclerpriceTV);
            locationTV = itemView.findViewById(R.id.recyclerLocationTV);
            editTV = itemView.findViewById(R.id.recyclereditTV);
            removeTV = itemView.findViewById(R.id.recyclerRemoveTV);

        }
    }


}
