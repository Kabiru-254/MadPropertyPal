package com.example.madpropertypal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.madpropertypal.adapters.OffersRecyclerAdapter;
import com.example.madpropertypal.adapters.RecyclerViewAdapter;
import com.example.madpropertypal.models.OfferModel;
import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.sqlite.OffersSQLiteHelpar;
import com.example.madpropertypal.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class SelectedHouseOffersActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<OfferModel> list=new ArrayList<>();
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_house_offers);

        id =  getIntent().getIntExtra("id",0);
        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        fetchOffers();
    }



    int id;
    private void fetchOffers() {

        if (!list.isEmpty()){
            list.clear();
        }

        OffersSQLiteHelpar sqLiteHelpar = new OffersSQLiteHelpar(getBaseContext(), "OFFERS_DB");

        if (id != 0){

            list = sqLiteHelpar.fetchOffers(id);

            if (!list.isEmpty()) {

                mAdapter = new OffersRecyclerAdapter(getBaseContext(), list);
                recyclerView.setAdapter(mAdapter);

            }else {

                Toast.makeText(this, "No Offers made on this house yet", Toast.LENGTH_SHORT).show();


            }

        }else {

            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }

    }
}