package com.example.madpropertypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class SelectedPropertyDetails extends AppCompatActivity {



    private  PropertyModel propertyModel;
    private MaterialAutoCompleteTextView nameET, typeET, leaseET, locationET,
            bedroomsCTV, bathroomsCTV, sizeCTV, priceCTV, amenitiesCTV, descriptionCTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_property_details);

        init();

        Intent intent = getIntent();
        propertyModel = (PropertyModel) intent.getSerializableExtra("selectedProperty");

        nameET.setText(propertyModel.getPropertyName());
        typeET.setText(propertyModel.getPropertyType());
        leaseET.setText(propertyModel.getLeaseType());
        locationET.setText(propertyModel.getLocation());
        amenitiesCTV.setText(propertyModel.getLocalAmenities());
        descriptionCTV.setText(propertyModel.getDescription());


        //integers to string
        bedroomsCTV.setText(String.valueOf(propertyModel.getBedroomNumber()));
        bathroomsCTV.setText(String.valueOf(propertyModel.getBathroomNumber()));
        sizeCTV.setText(String.valueOf(propertyModel.getSize()));
        priceCTV.setText(String.valueOf(propertyModel.getAskingPrice()));





        //ingredients = ingredients.replaceAll(",","\n"+ "\u25CF ");


    }

    private void init() {

        nameET = findViewById(R.id.nameCTV);
        typeET = findViewById(R.id.typeCTV);
        leaseET = findViewById(R.id.leaseCTV);
        locationET = findViewById(R.id.locationCTV);
        bedroomsCTV = findViewById(R.id.bedroomsCTV);
        bathroomsCTV = findViewById(R.id.bathroomsCTV);
        sizeCTV = findViewById(R.id.sizeCTV);
        priceCTV = findViewById(R.id.askingPriceCTV);
        amenitiesCTV = findViewById(R.id.amenitiesCTV);
        descriptionCTV = findViewById(R.id.descriptionCTV);
    }
}