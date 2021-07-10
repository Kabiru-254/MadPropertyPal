package com.example.madpropertypal.ui.myproperties;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madpropertypal.R;
import com.example.madpropertypal.adapters.EditorsRecyclerViewAdapter;
import com.example.madpropertypal.adapters.RecyclerViewAdapter;
import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class PropertiesFragment extends Fragment {

    RecyclerView recyclerView;
    List<PropertyModel> list=new ArrayList<>();
    RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View root = inflater.inflate(R.layout.properties_fragment, container, false);

         recyclerView = root.findViewById(R.id.recyclerView4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        fetchItems();
         return root;
    }

    private void fetchItems() {

        if (!list.isEmpty()){
            list.clear();
        }

        SQLiteHelper sqLiteHelpar = new SQLiteHelper(getContext(), "HOUSES_AVAILABLE");
        list = sqLiteHelpar.fetchHouses();

        mAdapter = new EditorsRecyclerViewAdapter(getContext(),list);
        recyclerView.setAdapter(mAdapter);
    }


}