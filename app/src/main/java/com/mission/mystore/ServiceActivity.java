package com.mission.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission.mystore.Adapter.CustomSpinnerAdapter;
import com.mission.mystore.Adapter.ServiceRecyclerViewAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    public static final String KEY_TYPE_SERVICE = "KEY_TYPE_SERVICE";
    public static final String KEY_TYPE_SERVICE_ORDER = "KEY_TYPE_SERVICE_ORDER";
    private static final String TAG = ServiceActivity.class.getSimpleName();
    private ArrayList<Vendor> mVendors;
    private ServiceRecyclerViewAdapter mRecyclerViewAdapter;
    private ArrayList<VendorItem> mSelectedVendorItems;
    private int mSelectedVendorIndex;
    private ServiceOrder mServiceServiceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rv_vendor_items);
        Spinner vendorSpinner = findViewById(R.id.spinner_vendors);

        String serviceName = getIntent().getStringExtra(KEY_TYPE_SERVICE);
        toolbar.setTitle(serviceName);

        Gson gson = new Gson();
        Type type = new TypeToken<ServiceOrder>(){}.getType();
        mServiceServiceOrder = gson.fromJson(getIntent().getStringExtra(KEY_TYPE_SERVICE_ORDER), type);
        if (mServiceServiceOrder == null) {
            mServiceServiceOrder = new ServiceOrder(serviceName);
        }

        mVendors = getVendors();
        mSelectedVendorItems = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,
                Utility.getNumOfColumns(this,
                        getResources().getDimension(R.dimen.width_vendor_item))));
        mRecyclerViewAdapter = new ServiceRecyclerViewAdapter(mSelectedVendorItems,
                new ServiceRecyclerViewAdapter.VendorItemCountListener() {
                    @Override
                    public void onVendorItemCountChangeListener(int itemIndex, int itemOrderCount) {
//                        mVendors.get(mSelectedVendorIndex).getVendorItems()
//                                .get(itemIndex).setOrderCount(itemOrderCount);
                        /* Update Service Order */
                        mServiceServiceOrder.updateOrder(
                                mVendors.get(mSelectedVendorIndex),
                                mVendors.get(mSelectedVendorIndex).getVendorItems().get(itemIndex), itemOrderCount);
                }
        });
        recyclerView.setAdapter(mRecyclerViewAdapter);

        vendorSpinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_item_vendor, mVendors));
        vendorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedVendorIndex = position;
                mSelectedVendorItems.clear();

                Vendor selectedVendor = mVendors.get(mSelectedVendorIndex);
                for (VendorItem vendorItem: selectedVendor.getVendorItems()) {
                    vendorItem.setOrderCount(mServiceServiceOrder.getOrderCount(selectedVendor.getId(), vendorItem.getId()));
                    mSelectedVendorItems.add(vendorItem);
                }
                mSelectedVendorItems.addAll(mVendors.get(mSelectedVendorIndex).getVendorItems());
                mRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        vendorSpinner.setSelection(mSelectedVendorIndex);
    }

    private ArrayList<Vendor> getVendors() {
        ArrayList<Vendor> vendors = new ArrayList<>();
        for (int vendor_index = 0; vendor_index < 10; vendor_index++) {
            Vendor vendor = new Vendor(100+vendor_index, "Sri Matha Kirana Store", "5.0", null);
            ArrayList<VendorItem> vendorItems = new ArrayList<>();
            for (int item_index = 0; item_index < 10; item_index++) {
                VendorItem vendorItem = new VendorItem(item_index, "Onions",
                        "https://cdn.theatlantic.com/assets/media/img/mt/2015/05/shutterstock_247399801/lead_720_405.jpg",
                        "1 Kg", 20);
                vendorItems.add(vendorItem);
            }
            vendor.setVendorItems(vendorItems);
            vendors.add(vendor);
        }
        return vendors;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResultServiceOrder();
        super.onBackPressed();
    }

    private void setResultServiceOrder() {
        Gson gson = new Gson();
        Intent intent = new Intent();
        intent.putExtra(KEY_TYPE_SERVICE_ORDER, gson.toJson(mServiceServiceOrder));
        setResult(RESULT_OK, intent);
    }
}
