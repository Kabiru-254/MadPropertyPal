package com.example.madpropertypal.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.R;
import com.example.madpropertypal.sqlite.SQLiteHelper;
import com.example.madpropertypal.ui.home.RecyclerViewAdapter;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    private Button searchBt;
    private SearchView searchView;
    private SwitchCompat switchCompat;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);


        switchCompat = root.findViewById(R.id.advancedSwitch);
        recyclerView = root.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);


        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    searchView.setVisibility(View.GONE);

                }else {

                    searchView.setVisibility(View.VISIBLE);

                }
            }
        });


        searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.isEmpty()){

                    recyclerView.setVisibility(View.GONE);

                }else {

                    searchHouse(query);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.isEmpty()){

                    recyclerView.setVisibility(View.GONE);

                }else {

                    searchHouse(newText);

                }
                return false;
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }







    List<PropertyModel> searchlist=new ArrayList<>();
    RecyclerView.Adapter mAdapter;


    private void searchHouse(String searchedHouse) {


        if (!searchlist.isEmpty()){


            searchlist.clear();

        }


        SQLiteHelper sqLiteHelpar = new SQLiteHelper(getContext(), "HOUSES_AVAILABLE");
        searchlist = sqLiteHelpar.searchHouses(searchedHouse);

        mAdapter = new RecyclerViewAdapter(getContext(),searchlist);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setVisibility(View.VISIBLE);

    }





}