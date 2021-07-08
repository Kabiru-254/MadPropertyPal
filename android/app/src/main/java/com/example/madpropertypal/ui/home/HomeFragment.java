package com.example.madpropertypal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madpropertypal.PropertyModel;
import com.example.madpropertypal.R;
import com.example.madpropertypal.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {



    RecyclerView recyclerView;
    List<PropertyModel> list=new ArrayList<>();
    RecyclerView.Adapter mAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchItems();



        return root;
    }



    @Override
    public void onPause() {
        list.clear();
        super.onPause();


    }


    @Override
    public void onResume() {
        fetchItems();
        super.onResume();

    }

    private void fetchItems() {



        if (!list.isEmpty()){
            list.clear();
        }



        SQLiteHelper sqLiteHelpar = new SQLiteHelper(getContext(), "HOUSES_AVAILABLE");
        list = sqLiteHelpar.fetchHouses();

        mAdapter = new RecyclerViewAdapter(getContext(),list);
        recyclerView.setAdapter(mAdapter);
    }
}