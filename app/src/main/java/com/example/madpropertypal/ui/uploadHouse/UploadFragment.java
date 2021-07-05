package com.example.madpropertypal.ui.uploadHouse;

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

import com.example.madpropertypal.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class UploadFragment extends Fragment {



    private MaterialButton button;
    private TextInputLayout propertyNameTET, propertyTypeTET,
            leaseTypeTET, locationTET, noOfBedroomsTET, noOfBathroomsTET,
            sizeTET, priceTET, amenitiesTET, descriptionTET;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_property_details, container, false);


        //initiate the views
        button = root.findViewById(R.id.submitBT);
        propertyNameTET = root.findViewById(R.id.propertyName);
        propertyTypeTET = root.findViewById(R.id.type);
        leaseTypeTET = root.findViewById(R.id.leaseType);
        locationTET = root.findViewById(R.id.location);
        noOfBedroomsTET = root.findViewById(R.id.bedrooms);
        noOfBathroomsTET = root.findViewById(R.id.bathrooms);
        sizeTET = root.findViewById(R.id.size);
        priceTET = root.findViewById(R.id.askingPrice);
        amenitiesTET = root.findViewById(R.id.localAmenities);
        descriptionTET = root.findViewById(R.id.description);



        checkRequiredFieldsForEmptyValues();
        return root;
    }





    //check if the required inputs are filled or not
    // in order to disable button

    
}