package com.example.madpropertypal;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madpropertypal.sqlite.OffersSQLiteHelpar;
import com.example.madpropertypal.sqlite.SQLiteHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;


import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;


public class OfferActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton button;
    RadioGroup offerRG;
    TextInputLayout priceTIL,dateTIL, offerConditionsTIL, commentsTIL;
    MaterialTextView expiryTIL;
    Calendar calendar,offerExpiryDate,offerDate;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_dialog);

        init();


    }



    String dateOfOffer;
    private void init() {

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());

        button = findViewById(R.id.submitOfferBT);
        button.setOnClickListener(this::onClick);


        offerRG = findViewById(R.id.interestRG);
        offerRG.check(R.id.firm);

        priceTIL = findViewById(R.id.offerPriceTIL);
        dateTIL = findViewById(R.id.viewDateTV);


        //get todays date
        calendar = Calendar.getInstance();
        offerDate = Calendar.getInstance();
        offerDate.set(Calendar.DATE,calendar.get(Calendar.DATE));
        offerDate.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
        offerDate.set(Calendar.YEAR,calendar.get(Calendar.YEAR));

        dateOfOffer = dateFormatter.format(offerDate.getTime());
        dateTIL.getEditText().setText(dateOfOffer);



        expiryTIL = findViewById(R.id.offerExpiry);
        expiryTIL.setOnClickListener(this::onClick);


        offerConditionsTIL = findViewById(R.id.offerConditions);
        commentsTIL = findViewById(R.id.offerComments);



        offerExpiryDate = Calendar.getInstance();



    }






    int selectedRadio;
    RadioButton radioButton;
    String interest, conditions, comments, price;
    int offerPrice;
    @Override
    public void onClick(View v) {

        if (v == expiryTIL){

            showDatePicker();

        }else  if (v == button){


            selectedRadio = offerRG.getCheckedRadioButtonId();
            radioButton = findViewById(selectedRadio);
            interest = radioButton.getText().toString();

            conditions = offerConditionsTIL.getEditText().getText().toString();
            price = priceTIL.getEditText().getText().toString();

            if (!interest.isEmpty() || !dateOfOffer.isEmpty()){

                if (interest.equals("Conditional") && conditions.isEmpty()){

                    offerConditionsTIL.setError("Kindly set the conditions for your interest");
                    offerConditionsTIL.requestFocus();

                }else if (interest.equals("Firm") && price.isEmpty()){

                    priceTIL.requestFocus();
                    priceTIL.setError("Kindly set the price for your offer");

                }else {

                    submitOffer();

                }

            }else {

                Toast.makeText(this, "Kindly select your interest", Toast.LENGTH_SHORT).show();
            }


        }



    }





    OfferModel offerModel;
    private void submitOffer() {

        //empty string for price causes a crash
        if (price.isEmpty()){

            offerPrice = 0;

        }else{

            offerPrice = Integer.parseInt(price);
        }


        if (conditions.isEmpty()){

            conditions = " ";

        }else  if (price.isEmpty()){

            offerPrice = 0;

        }else if (comments.isEmpty()){

            comments = " ";

        }else if (expiry.isEmpty()){

            expiry = " ";
        }


        OffersSQLiteHelpar sqLiteHelpar = new OffersSQLiteHelpar(getBaseContext(), "OFFERS_DB",1);
        offerModel = new OfferModel(dateOfOffer, interest, offerPrice, expiry,  conditions, comments);

        Boolean submitOffer = sqLiteHelpar.makeOffer(offerModel);

        if (submitOffer) {


            Intent intent = new Intent(this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(this, "Property Added Successfully " , Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Something Went Wrong. Try Again", Toast.LENGTH_SHORT).show();

        }


    }



    String expiry;
    public void showDatePicker(){

        datePickerDialog = new DatePickerDialog(OfferActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                offerExpiryDate.set(year, monthOfYear, dayOfMonth);
                expiry = dateFormatter.format(offerExpiryDate.getTime());
                expiryTIL.setText(expiry);

            }},calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
