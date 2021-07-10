package com.example.madpropertypal.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.R;
import com.example.madpropertypal.sqlite.SQLiteHelper;
import com.example.madpropertypal.adapters.RecyclerViewAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    private Button searchBt;
    private SearchView searchView;
    private SwitchCompat switchCompat;
    TextInputLayout locationTIL,typeTIL,bedroomsTIL;


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkRequiredFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };




    private void checkRequiredFieldsForEmptyValues() {

        location = locationTIL.getEditText().getText().toString();
        type = typeTIL.getEditText().getText().toString();
       String room = bedroomsTIL.getEditText().getText().toString();



        if (location.isEmpty() || type.isEmpty() || room.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            searchBt.setEnabled(false);

        }else {

            bedrooms = Integer.parseInt(room);
            searchBt.setEnabled(true);

        }

    }





    String location, type;
    int bedrooms;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);


        switchCompat = root.findViewById(R.id.advancedSwitch);
        recyclerView = root.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        locationTIL = root.findViewById(R.id.searchlocation);
        typeTIL = root.findViewById(R.id.searchTypeTIL);
        bedroomsTIL = root.findViewById(R.id.searchBedrooms);

        locationTIL.getEditText().addTextChangedListener(textWatcher);
        typeTIL.getEditText().addTextChangedListener(textWatcher);
        bedroomsTIL.getEditText().addTextChangedListener(textWatcher);

        searchBt = root.findViewById(R.id.searchBT);
        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchlist.isEmpty()){

                    searchlist.clear();
                }
                SQLiteHelper sqLiteHelpar = new SQLiteHelper(getContext(), "HOUSES_AVAILABLE");
                searchlist = sqLiteHelpar.advancedSearchHouses(location,bedrooms,type);

                if (searchlist.isEmpty()){
                    Toast.makeText(getContext(), "No Items Matching The Search Found", Toast.LENGTH_SHORT).show();

                }else {

                    mAdapter = new RecyclerViewAdapter(getContext(),searchlist);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setVisibility(View.VISIBLE);

                }
            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    searchView.setVisibility(View.GONE);
                    root.findViewById(R.id.advancedLL).setVisibility(View.VISIBLE);
                    checkRequiredFieldsForEmptyValues();

                }else {

                    searchView.setVisibility(View.VISIBLE);
                    root.findViewById(R.id.advancedLL).setVisibility(View.GONE);

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