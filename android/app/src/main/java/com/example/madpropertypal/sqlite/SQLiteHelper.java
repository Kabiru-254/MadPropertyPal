package com.example.madpropertypal.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.madpropertypal.models.PropertyModel;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {


    public static final String HOUSES_TABLE = "HOUSES_TABLE";
    public static final String ID = "ID";
    public static final String PROPERTY_NAME = "PROPERTY_NAME";
    public static final String PROPERTY_TYPE = "PROPERTY_TYPE";
    public static final String LEASE_TYPE = "LEASE_TYPE";
    public static final String LOCATION = "LOCATION";
    public static final String LOCAL_AMENITIES = "LOCAL_AMENITIES";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String BEDROOM_NUMBERS = "BEDROOM_NUMBERS";
    public static final String BATHROOM_NUMBERS = "BATHROOM_NUMBERS";
    public static final String PROPERTY_SIZE = "PROPERTY_SIZE";
    public static final String ASKING_PRICE = "ASKING_PRICE";



    public SQLiteHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);

    }





    //creates the db;
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + HOUSES_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROPERTY_NAME + " TEXT, "
                + PROPERTY_TYPE + " TEXT, "
                + LEASE_TYPE + " TEXT, "
                + LOCATION + " TEXT, "
                + LOCAL_AMENITIES + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + BEDROOM_NUMBERS + " INT, "
                + BATHROOM_NUMBERS + " INT, "
                + PROPERTY_SIZE + " INT, "
                + ASKING_PRICE + " INT);";

        db.execSQL(createTableStatement);


    }







    public boolean addHouses(PropertyModel propertyModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PROPERTY_NAME, propertyModel.getPropertyName());
        cv.put(PROPERTY_TYPE, propertyModel.getPropertyType());
        cv.put(LEASE_TYPE, propertyModel.getLeaseType());
        cv.put(LOCATION, propertyModel.getLocation());
        cv.put(LOCAL_AMENITIES, propertyModel.getLocalAmenities());
        cv.put(DESCRIPTION, propertyModel.getDescription());
        cv.put(BEDROOM_NUMBERS, propertyModel.getBedroomNumber());
        cv.put(BATHROOM_NUMBERS, propertyModel.getBathroomNumber());
        cv.put(ASKING_PRICE, propertyModel.getAskingPrice());
        cv.put(PROPERTY_SIZE, propertyModel.getSize());


        long insert = db.insert(HOUSES_TABLE, null, cv);


        if (insert == -1){

            db.close();
            return false;

        }else {

            db.close();
            return true;

        }
    }




    public Boolean updateProperty(PropertyModel propertyModel,int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();



        cv.put(PROPERTY_NAME, propertyModel.getPropertyName());
        cv.put(PROPERTY_TYPE, propertyModel.getPropertyType());
        cv.put(LEASE_TYPE, propertyModel.getLeaseType());
        cv.put(LOCATION, propertyModel.getLocation());
        cv.put(LOCAL_AMENITIES, propertyModel.getLocalAmenities());
        cv.put(DESCRIPTION, propertyModel.getDescription());
        cv.put(BEDROOM_NUMBERS, propertyModel.getBedroomNumber());
        cv.put(BATHROOM_NUMBERS, propertyModel.getBathroomNumber());
        cv.put(ASKING_PRICE, propertyModel.getAskingPrice());
        cv.put(PROPERTY_SIZE, propertyModel.getSize());


        long update = db.update(HOUSES_TABLE, cv, ID +" = ?", new String[]{String.valueOf(id)});

        if (update == -1 ){

            db.close();
            return false;

        }else {

            db.close();
            return true;

        }



    }



    public List<PropertyModel> fetchHouses(){

        List<PropertyModel> propertyModelList = new ArrayList<>();

        String fetchQuery = "SELECT * FROM " + HOUSES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(fetchQuery,null);

        if (cursor.moveToFirst()){
            do {

                int id = cursor.getInt(0);
                String propertyName = cursor.getString(1);
                String type = cursor.getString(2);
                String lease = cursor.getString(3);
                String location = cursor.getString(4);
                String amenities = cursor.getString(5);
                String description = cursor.getString(6);
                int bedrooms = cursor.getInt(7);
                int bathrooms = cursor.getInt(8);
                int size = cursor.getInt(9);
                int price = cursor.getInt(10);



                PropertyModel model = new PropertyModel(id,propertyName,type,lease,location,amenities,
                        description,bedrooms,bathrooms,size,price);
                propertyModelList.add(model);

            }while (cursor.moveToNext());

        }else{


        }

        cursor.close();
        db.close();

        return propertyModelList;

    }






    public List<PropertyModel> searchHouses(String searchedString) {

        List<PropertyModel> propertyList = new ArrayList<>();

        String searchHouse = "SELECT * FROM " +HOUSES_TABLE + " WHERE " +PROPERTY_NAME+ " LIKE '%" + searchedString + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(searchHouse,null);


        if (cursor.moveToFirst()){
            do {

                int id = cursor.getInt(0);
                String propertyName = cursor.getString(1);
                String type = cursor.getString(2);
                String lease = cursor.getString(3);
                String location = cursor.getString(4);
                String amenities = cursor.getString(5);
                String description = cursor.getString(6);
                int bedrooms = cursor.getInt(7);
                int bathrooms = cursor.getInt(8);
                int size = cursor.getInt(9);
                int price = cursor.getInt(10);



                PropertyModel model = new PropertyModel(id,propertyName,type,lease,location,amenities,
                        description,bedrooms,bathrooms,size,price);
                propertyList.add(model);

            }while (cursor.moveToNext());


        }else{


        }

        cursor.close();
        db.close();
        return propertyList;

    }





    public List<PropertyModel> advancedSearchHouses(String searchedString, int searchedBedrooms, String searchedType) {

        List<PropertyModel> propertyList = new ArrayList<>();

        String searchHouse = "SELECT * FROM " +HOUSES_TABLE + " WHERE " + LOCATION + " LIKE '%" + searchedString + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(searchHouse,null);


        if (cursor.moveToFirst()){
            do {

                int id = cursor.getInt(0);
                String propertyName = cursor.getString(1);
                String type = cursor.getString(2);
                String lease = cursor.getString(3);
                String location = cursor.getString(4);
                String amenities = cursor.getString(5);
                String description = cursor.getString(6);
                int bedrooms = cursor.getInt(7);
                int bathrooms = cursor.getInt(8);
                int size = cursor.getInt(9);
                int price = cursor.getInt(10);

                if (bedrooms == searchedBedrooms && type.equals(searchedType)) {

                    PropertyModel model = new PropertyModel(id, propertyName, type, lease, location, amenities,
                            description, bedrooms, bathrooms, size, price);
                    propertyList.add(model);

                }

            }while (cursor.moveToNext());


        }else{


        }

        cursor.close();
        db.close();
        return propertyList;

    }








    public Boolean removeProperty(PropertyModel propertyModel){

        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " +HOUSES_TABLE+ " WHERE " +ID+ " = " + propertyModel.getId();

        Cursor cursor = db.rawQuery(deleteQuery, null);
        if (cursor.moveToFirst()){

            return true;

        }else {

            return false;

        }

    }




    public void dropTB(){


        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + HOUSES_TABLE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HOUSES_TABLE);
        onCreate(db);

    }
}
