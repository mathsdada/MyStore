package com.mission.mystore.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mission.mystore.R;
import com.mission.mystore.Vendor;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private ArrayList mAdapterData;
    private LayoutInflater mInflator;
    private int mLayoutResource;

    public CustomSpinnerAdapter(Activity activity, int resource, ArrayList adapterData) {
        super(activity, resource, adapterData);

        mAdapterData = adapterData;
        mLayoutResource = resource;
        mInflator = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = mInflator.inflate(mLayoutResource, parent, false);
        TextView vendorNameTextView = row.findViewById(R.id.tv_vendor_name);
        TextView vendorRatingTextView = row.findViewById(R.id.tv_vendor_rating);
        vendorNameTextView.setText(((Vendor) mAdapterData.get(position)).getName());
        vendorRatingTextView.setText(((Vendor) mAdapterData.get(position)).getRating());
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = mInflator.inflate(mLayoutResource, parent, false);
        TextView vendorNameTextView = row.findViewById(R.id.tv_vendor_name);
        TextView vendorRatingTextView = row.findViewById(R.id.tv_vendor_rating);
        vendorNameTextView.setText(((Vendor) mAdapterData.get(position)).getName());
        vendorRatingTextView.setText(((Vendor) mAdapterData.get(position)).getRating());
        return row;

    }


}
