package com.example.madpropertypal.ui.uploadHouse;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.madpropertypal.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class UploadFragment extends Fragment implements View.OnClickListener {



    private MaterialButton button;
    private TextInputLayout propertyNameTET, propertyTypeTET,
            leaseTypeTET, locationTET, noOfBedroomsTET, noOfBathroomsTET,
            sizeTET, priceTET, amenitiesTET, descriptionTET;






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
        propertyNameTET.getEditText().addTextChangedListener(textWatcher);
        propertyTypeTET.getEditText().addTextChangedListener(textWatcher);
        leaseTypeTET.getEditText().addTextChangedListener(textWatcher);
        locationTET.getEditText().addTextChangedListener(textWatcher);
        noOfBedroomsTET.getEditText().addTextChangedListener(textWatcher);
        noOfBathroomsTET.getEditText().addTextChangedListener(textWatcher);
        sizeTET.getEditText().addTextChangedListener(textWatcher);
        priceTET.getEditText().addTextChangedListener(textWatcher);
        amenitiesTET.getEditText().addTextChangedListener(textWatcher);
        descriptionTET.getEditText().addTextChangedListener(textWatcher);

        button.setOnClickListener(this::onClick);
        return root;
    }





    //check if the required inputs are filled or not
    // in order to disable button

    private String propertyName, propertyType, leaseType, location,
            amenities, description, noOfBedrooms, noOfBathrooms, size, price;

    private void checkRequiredFieldsForEmptyValues() {


        propertyName = propertyNameTET.getEditText().getText().toString();
        propertyType = propertyNameTET.getEditText().getText().toString();
        leaseType = leaseTypeTET.getEditText().getText().toString();
        location = locationTET.getEditText().getText().toString();
        noOfBedrooms = noOfBedroomsTET.getEditText().getText().toString();
        noOfBathrooms = noOfBathroomsTET.getEditText().getText().toString();
        size = sizeTET.getEditText().getText().toString();
        price = priceTET.getEditText().getText().toString();



        if (propertyName.isEmpty() || propertyType.isEmpty() ||
                leaseType.isEmpty() || location.isEmpty() ||
                noOfBedrooms.isEmpty() || noOfBathrooms.isEmpty()
                || size.isEmpty() || price.isEmpty()) {

            button.setEnabled(false);

        }else{

            button.setEnabled(true);
        }

    }






    //what happens when button is clicked
    @Override
    public void onClick(View v) {


        if (v == button){

            Toast.makeText(getContext(), "button clicked", Toast.LENGTH_SHORT).show();
            openDialog(R.layout.dialog_layout);

        }else if (v == cancelBT){


            Toast.makeText(getContext(), "Make your changes then submit again", Toast.LENGTH_SHORT).show();
            dialog.dismiss();

        } else if (v == confirmBT) {

            Toast.makeText(getContext(), "Confirmed", Toast.LENGTH_SHORT).show();

        }
    }










    //adding the confirmation dialog
    Dialog dialog;
    TextView nameTV, typeTV, leaseTypeTV, locationTV, localAmenitiesTV,
            descriptionTV, bathroomNumber, bedroomNumber,sizeTV;
    Button confirmBT, cancelBT;
    public void openDialog(int layoutID){

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View inflator = layoutInflater.inflate(layoutID, null);

        dialog = new Dialog(getContext());
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        dialog.setTitle(null);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setContentView(inflator);

        //selection buttons onclick
        nameTV =  inflator.findViewById(R.id.nameTV);
        typeTV =  inflator.findViewById(R.id.nameTV);

        
        nameTV.setText(propertyName);
        typeTV.setText(propertyType);


        cancelBT = inflator.findViewById(R.id.cancelBT);
        cancelBT.setOnClickListener(this::onClick);
        confirmBT = inflator.findViewById(R.id.confirmBT);
        confirmBT.setOnClickListener(this::onClick);

        dialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);

    }
}