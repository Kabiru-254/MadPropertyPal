package com.example.madpropertypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PropertyDetails extends AppCompatActivity {

    private MaterialButton button;
    private TextInputLayout nameTET;




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


        String y = nameTET.getEditText().getText().toString();

        if (y.isEmpty()){

            button.setEnabled(false);

        }else{

            button.setEnabled(true);
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);


        button = findViewById(R.id.submitBT);
        nameTET = findViewById(R.id.propertyName);


        nameTET.getEditText().addTextChangedListener(textWatcher);
        checkRequiredFieldsForEmptyValues();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String x = nameTET.getEditText().getText().toString();
                Toast.makeText(PropertyDetails.this, x, Toast.LENGTH_SHORT).show();
            }
        });


    }





}