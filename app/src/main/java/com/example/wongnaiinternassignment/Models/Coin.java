package com.example.wongnaiinternassignment.Models;

public class Coin {
    private String mName;
    private String mDescription;
    private String mImageURL;

    public  Coin(){}
    public Coin(String name, String description, String imageURL){
        this.mName = name;
        this.mDescription = description;
        this.mImageURL = imageURL;

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }
}
