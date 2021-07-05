package com.example.madpropertypal;

public class PropertyModel {

    //This class defines the basic structure of a property item and the details to be stored on the db

    String propertyName, propertyType, leaseType, location, localAmenities, description ;
    int bedroomNumber, bathroomNumber, size,askingPrice;



    //empty constructor;
    public  PropertyModel(){}



    //constructor
    public PropertyModel(String propertyName, String propertyType,
                         String leaseType, String location, String localAmenities,
                         String description, int bedroomNumber, int bathroomNumber,
                         int size, int askingPrice) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.leaseType = leaseType;
        this.location = location;
        this.localAmenities = localAmenities;
        this.description = description;
        this.bedroomNumber = bedroomNumber;
        this.bathroomNumber = bathroomNumber;
        this.size = size;
        this.askingPrice = askingPrice;
    }




    //getter and setter methods;
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocalAmenities() {
        return localAmenities;
    }

    public void setLocalAmenities(String localAmenities) {
        this.localAmenities = localAmenities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBedroomNumber() {
        return bedroomNumber;
    }

    public void setBedroomNumber(int bedroomNumber) {
        this.bedroomNumber = bedroomNumber;
    }

    public int getBathroomNumber() {
        return bathroomNumber;
    }

    public void setBathroomNumber(int bathroomNumber) {
        this.bathroomNumber = bathroomNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(int askingPrice) {
        this.askingPrice = askingPrice;
    }
}
