package com.mission.mystore;

public class VendorItem {
    private int mId;
    private String mName;
    private String mUrl;
    private String mUnit;
    private int mUnitPrice;
    private int mOrderCount = 0;

    public VendorItem(int id, String name, String url, String unit, int unitPrice) {
        mId = id;
        mName = name;
        mUrl = url;
        mUnit = unit;
        mUnitPrice = unitPrice;
    }

    public VendorItem(VendorItem item) {
        this(item.mId, item.mName, item.mUrl, item.mUnit, item.mUnitPrice, item.mOrderCount);
    }

    public VendorItem(int id, String name, String url, String unit, int unitPrice, int orderCount) {
        mId = id;
        mName = name;
        mUrl = url;
        mUnit = unit;
        mUnitPrice = unitPrice;
        mOrderCount = orderCount;
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

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

    public int getUnitPrice() {
        return mUnitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        mUnitPrice = unitPrice;
    }

    public int getOrderCount() {
        return mOrderCount;
    }

    public void setOrderCount(int orderCount) {
        mOrderCount = orderCount;
    }
}
