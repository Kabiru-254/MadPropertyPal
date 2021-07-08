package com.example.madpropertypal.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.PropertyModel;
import com.example.madpropertypal.R;
import com.example.madpropertypal.sqlite.SQLiteHelper;
import com.example.madpropertypal.ui.home.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    private Button searchBt;
    private SearchView searchView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);


        searchBt = root.findViewById(R.id.searchBT);


        searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchHouse(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchHouse(newText);
                return false;
            }
        });

        recyclerView = root.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
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

    }





}