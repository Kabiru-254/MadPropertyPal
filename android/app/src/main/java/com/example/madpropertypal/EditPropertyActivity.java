package com.example.madpropertypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madpropertypal.models.PropertyModel;
import com.example.madpropertypal.sqlite.SQLiteHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class EditPropertyActivity extends AppCompatActivity implements View.OnClickListener {


    private PropertyModel propertyModel,updatePropModel;
    private MaterialAutoCompleteTextView nameET, typeET, leaseET, locationET,
            bedroomsCTV, bathroomsCTV, sizeCTV, priceCTV, amenitiesCTV, descriptionCTV;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_property);


        Intent intent = getIntent();
        propertyModel = (PropertyModel) intent.getSerializableExtra("EditProperty");

        init();




    }





    private MaterialButton editBT;
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



        editBT = findViewById(R.id.editBT);
        editBT.setOnClickListener(this);



        nameET.setText(propertyModel.getPropertyName());
        typeET.setText(propertyModel.getPropertyType());
        leaseET.setText(propertyModel.getLeaseType());
        locationET.setText(propertyModel.getLocation());
        descriptionCTV.setText(propertyModel.getDescription());


        //integers to string
        bedroomsCTV.setText(String.valueOf(propertyModel.getBedroomNumber()));
        bathroomsCTV.setText(String.valueOf(propertyModel.getBathroomNumber()));
        sizeCTV.setText(String.valueOf(propertyModel.getSize()));
        priceCTV.setText(String.valueOf(propertyModel.getAskingPrice()));
        amenitiesCTV.setText(propertyModel.getLocalAmenities());



    }








    @Override
    public void onClick(View v) {

        if (v == editBT){


            updateProperty();
        }
    }





    private String propertyName, propertyType, leaseType, location,
            amenities, description, noOfBedrooms, noOfBathrooms, size, price;

    private void updateProperty() {

        propertyName  = nameET.getText().toString().trim();
        propertyType  = typeET.getText().toString().trim();
        leaseType  = leaseET.getText().toString().trim();
        location  = locationET.getText().toString().trim();
        amenities  = amenitiesCTV.getText().toString().trim();
        description  = descriptionCTV.getText().toString().trim();
        noOfBedrooms  = bedroomsCTV.getText().toString().trim();
        noOfBathrooms  = bathroomsCTV.getText().toString().trim();
        size  = sizeCTV.getText().toString().trim();
        price  = priceCTV.getText().toString().trim();


        if (propertyName.isEmpty() || propertyType.isEmpty() ||leaseType.isEmpty() || location.isEmpty()
                || noOfBathrooms.isEmpty() || noOfBedrooms.isEmpty() || size.isEmpty() || price.isEmpty()){


            Toast.makeText(this, "Missing required Values", Toast.LENGTH_SHORT).show();

        }else {


            if (amenities.isEmpty()){

                amenities = " ";
            }

            if (description.isEmpty()){

                description = " ";
            }


            SQLiteHelper sqLiteHelpar = new SQLiteHelper(getBaseContext(), "HOUSES_AVAILABLE");

            int bedrooms = Integer.parseInt(noOfBedrooms);
            int bathrooms = Integer.parseInt(noOfBathrooms);
            int propertySize = Integer.parseInt(size);
            int askingPrice = Integer.parseInt(price);


            updatePropModel = new PropertyModel(propertyModel.getId(), propertyName, propertyType,leaseType,
                    location, amenities, description, bedrooms, bathrooms,propertySize, askingPrice );


            Boolean x = sqLiteHelpar.updateProperty(updatePropModel,propertyModel.getId());

            if (x){

                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));


            }else {

                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
            }
        }


    }
}