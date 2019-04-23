package com.mission.mystore;

import java.util.ArrayList;

public class ServiceOrder {
    private String mServiceName;
    private ArrayList<Vendor> mVendors;

    public ServiceOrder(String serviceName, ArrayList<Vendor> vendors) {
        mServiceName = serviceName;
        mVendors = vendors;
    }

    public ServiceOrder(String serviceName) {
        mServiceName = serviceName;
        mVendors = new ArrayList<>();
    }

    public void updateOrder(Vendor vendor, VendorItem item, int itemOrderCount) {
        int localVendorIndex, localVendorItemIndex;
        Vendor localVendor;
        VendorItem localVendorItem;

        localVendorIndex = findVendorIndexById(vendor.getId());
        if (localVendorIndex < 0) {
            localVendor = new Vendor(vendor);
            mVendors.add(localVendor);
        } else {
            localVendor = mVendors.get(localVendorIndex);
        }

        localVendorItemIndex = findVendorItemIndexById(localVendor, item.getId());
        if (localVendorItemIndex < 0) {
            localVendorItem = new VendorItem(item);
            localVendor.getVendorItems().add(localVendorItem);
        } else {
            localVendorItem = localVendor.getVendorItems().get(localVendorItemIndex);
        }

        if (itemOrderCount != 0) {
            localVendorItem.setOrderCount(itemOrderCount);
        } else {
            /* remove vendor Item from Vendor. Remove vendor from vendors list if there are no items in the vendor */
            localVendor.getVendorItems().remove(localVendorItemIndex);
            if (localVendor.getVendorItems().size() == 0) {
                mVendors.remove(localVendorIndex);
            }
        }
    }

    public int getOrderCount(int vendorId, int itemId) {
        int localVendorIndex, localVendorItemIndex;

        localVendorIndex = findVendorIndexById(vendorId);
        if (localVendorIndex < 0) return 0;
        localVendorItemIndex = findVendorItemIndexById(mVendors.get(localVendorIndex), itemId);
        if (localVendorItemIndex < 0) return 0;
        return mVendors.get(localVendorIndex).getVendorItems().get(localVendorItemIndex).getOrderCount();
    }

    private int findVendorIndexById(int vendorId) {
        for (int vendorIndex = 0; vendorIndex < mVendors.size(); vendorIndex++) {
            if (mVendors.get(vendorIndex).getId() == vendorId) {
                return vendorIndex;
            }
        }
        return -1;
    }

    private int findVendorItemIndexById(Vendor vendor, int vendorItemId) {
        ArrayList<VendorItem> vendorItems = vendor.getVendorItems();
        for (int itemIndex = 0; itemIndex < vendorItems.size(); itemIndex++) {
            if (vendorItems.get(itemIndex).getId() == vendorItemId) {
                return itemIndex;
            }
        }
        return -1;
    }

    public String getServiceName() {
        return mServiceName;
    }

    public void setServiceName(String serviceName) {
        mServiceName = serviceName;
    }

    public ArrayList<Vendor> getVendors() {
        return mVendors;
    }

    public void setVendors(ArrayList<Vendor> vendors) {
        mVendors = vendors;
    }
}
