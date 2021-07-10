package com.example.madpropertypal.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.madpropertypal.models.OfferModel;
import com.example.madpropertypal.models.PropertyModel;

import java.util.ArrayList;
import java.util.List;

public class OffersSQLiteHelpar extends SQLiteOpenHelper {


    public static final String ID1 = "ID";
    public static final String DATE_OF_OFFER = "DATE_OF_OFFER";
    public static final String INTEREST = "INTEREST";
    public static final String OFFER_PRICE = "OFFER_PRICE";
    public static final String PROPERTY_ID = "PROPERTY_ID";
    public static final String OFFER_EXPIRY_DATE = "OFFER_EXPIRY_DATE";
    public static final String CONDITIONS_OF_OFFER = "CONDITIONS_OF_OFFER";
    public static final String COMMENTS = "COMMENTS";
    public static final String OFFERS_TABLE = "OFFERS_TABLE";

    public OffersSQLiteHelpar(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createOffersTable = "CREATE TABLE " + OFFERS_TABLE + " (" + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_OF_OFFER + " TEXT, "
                + INTEREST + " TEXT, "
                + OFFER_PRICE + " INT, "
                + PROPERTY_ID + " INT, "
                + OFFER_EXPIRY_DATE + " TEXT, "
                + CONDITIONS_OF_OFFER + " TEXT, "
                + COMMENTS + " TEXT);";


        db.execSQL(createOffersTable);
    }







    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + OFFERS_TABLE);
        onCreate(db);
    }






    public  Boolean makeOffer(OfferModel offerModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues offerCV = new ContentValues();

        offerCV.put(DATE_OF_OFFER,offerModel.getOfferDate());
        offerCV.put(PROPERTY_ID,offerModel.getProperty_id());
        offerCV.put(INTEREST,offerModel.getInterest());
        offerCV.put(OFFER_PRICE,offerModel.getOfferPrice());
        offerCV.put(OFFER_EXPIRY_DATE,offerModel.getExpiryDate());
        offerCV.put(CONDITIONS_OF_OFFER,offerModel.getConditions());
        offerCV.put(COMMENTS,offerModel.getComments());

        long insertOffer = db.insert(OFFERS_TABLE,null, offerCV);


        if (insertOffer == -1){

            db.close();
            return false;

        }else {

            db.close();
            return true;
        }
    }






    public List<OfferModel> fetchOffers(int propertyID){

        List<OfferModel> modelList = new ArrayList<>();

        String fetchQuery = "SELECT * FROM " + OFFERS_TABLE+ " WHERE " + PROPERTY_ID + " = "+ propertyID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(fetchQuery,null);

        if (cursor.moveToFirst()){
            do {

                int id = cursor.getInt(0);
                String offerDate = cursor.getString(1);
                String interest = cursor.getString(2);
                int offerPrice = cursor.getInt(3);
                int propertyId = cursor.getInt(4);
                String expDate = cursor.getString(5);
                String conditions = cursor.getString(6);
                String comments = cursor.getString(7);





                OfferModel model = new OfferModel(offerDate, interest, expDate, conditions, comments, offerPrice, id, propertyId );
                modelList.add(model);

            }while (cursor.moveToNext());

        }else{


        }

        cursor.close();
        db.close();

        return modelList;

    }
}
