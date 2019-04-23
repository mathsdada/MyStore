package com.mission.mystore;

import java.util.ArrayList;

public class Vendor {
    private int mId;
    private String mName;
    private String mRating;
    private ArrayList<VendorItem> mVendorItems;

    public Vendor(int id, String name, String rating, ArrayList<VendorItem> vendorItems) {
        mId = id;
        mName = name;
        mRating = rating;
        mVendorItems = vendorItems;
    }

    public Vendor(int id, String name, String rating) {
        mId = id;
        mName = name;
        mRating = rating;
        mVendorItems = new ArrayList<>();
    }

    public Vendor(Vendor vendor) {
        this(vendor.mId, vendor.mName, vendor.mRating);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public ArrayList<VendorItem> getVendorItems() {
        return mVendorItems;
    }

    public void setVendorItems(ArrayList<VendorItem> vendorItems) {
        mVendorItems = vendorItems;
    }
}
