package com.example.madpropertypal.models;

public class OfferModel {

    String offerDate, interest, expiryDate, conditions, comments;
    int offerPrice, id, property_id;
    public OfferModel() {
    }


    public OfferModel(String offerDate, String interest, int offerPrice, int property_id,
                      String expiryDate, String conditions, String comments) {


        this.offerDate = offerDate;
        this.interest = interest;
        this.offerPrice = offerPrice;
        this.expiryDate = expiryDate;
        this.conditions = conditions;
        this.comments = comments;
        this.property_id = property_id;

    }


    public OfferModel(String offerDate, String interest, String expiryDate, String conditions,
                      String comments, int offerPrice, int id, int property_id) {

        this.offerDate = offerDate;
        this.interest = interest;
        this.expiryDate = expiryDate;
        this.conditions = conditions;
        this.comments = comments;
        this.offerPrice = offerPrice;
        this.id = id;
        this.property_id = property_id;
    }


    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }






}
