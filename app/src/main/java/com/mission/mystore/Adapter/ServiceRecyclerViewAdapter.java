package com.mission.mystore.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mission.mystore.R;
import com.mission.mystore.Service;
import com.mission.mystore.VendorItem;

import java.util.ArrayList;

public class ServiceRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String ITEM_TYPE_SERVICE = "ITEM_TYPE_SERVICE";
    private static final String ITEM_TYPE_VENDOR_ITEM = "ITEM_TYPE_VENDOR_ITEM";
    private static final String TAG = ServiceRecyclerViewAdapter.class.getSimpleName();
    private ArrayList<Service> mServices;
    private ServiceClickListener mServiceItemClickListener;
    private ArrayList<VendorItem> mVendorItems;
    private VendorItemCountListener mVendorItemCountListener;
    private String mItemType;

    public interface ServiceClickListener {
        void onServiceItemClickListener(int pos);
    }

    public interface VendorItemCountListener {
        void onVendorItemCountChangeListener(int itemIndex, int itemOrderCount);
    }
    public ServiceRecyclerViewAdapter(ArrayList<Service> services, ServiceClickListener listener) {
        mItemType = ITEM_TYPE_SERVICE;
        mServices = services;
        mServiceItemClickListener = listener;
    }

    public ServiceRecyclerViewAdapter(ArrayList<VendorItem> vendorItems, VendorItemCountListener listener) {
        mItemType = ITEM_TYPE_VENDOR_ITEM;
        mVendorItems = vendorItems;
        mVendorItemCountListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (mItemType.equals(ITEM_TYPE_SERVICE)) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_service, viewGroup, false);
            return new ServiceViewHolder(view);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_vendor_item, viewGroup, false);
            return new VendorItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (mItemType.equals(ITEM_TYPE_SERVICE)) {
            ((ServiceViewHolder) viewHolder).bindView(mServices.get(i));
        } else {
            ((VendorItemViewHolder) viewHolder).bindView(mVendorItems.get(i));
        }
    }

    @Override
    public int getItemCount() {
        if (mItemType.equals(ITEM_TYPE_SERVICE)) {
            return mServices.size();
        } else {
            return mVendorItems.size();
        }
    }

    private class ServiceViewHolder extends RecyclerView.ViewHolder {
        private ImageView mServiceImageView;
        private TextView mServiceTextView;

        public ServiceViewHolder(View serviceView) {
            super(serviceView);
            mServiceImageView = serviceView.findViewById(R.id.iv_service_icon);
            mServiceTextView = serviceView.findViewById(R.id.tv_service_name);
            serviceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mServiceItemClickListener != null) {
                        mServiceItemClickListener.onServiceItemClickListener(getAdapterPosition());
                    }
                }
            });
        }

        public void bindView(Service service) {
            mServiceImageView.setImageResource(service.getIcon());
            mServiceTextView.setText(service.getName());
        }
    }

    private class VendorItemViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private int mItemQuantity = 0;
        private TextView mItemNameTextView, mItemPriceTextView, mItemQuantityTextView;
        private ImageView mItemImageView;
        private ImageButton mAddImageButton, mRemoveImageButton;
        private Button mAddButton;

        public VendorItemViewHolder(View view) {
            super(view);
            mView = view;
            mItemNameTextView = view.findViewById(R.id.tv_vendor_item_name);
            mItemImageView = view.findViewById(R.id.iv_vendor_item_image);
            mItemPriceTextView = view.findViewById(R.id.tv_vendor_item_price);
            mItemQuantityTextView = view.findViewById(R.id.tv_vendor_item_count);
            mAddImageButton = view.findViewById(R.id.ib_vendor_item_add);
            mRemoveImageButton = view.findViewById(R.id.ib_vendor_item_remove);
            mAddButton = view.findViewById(R.id.button_vendor_item_add);

            mAddImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateQuantity(true);
                }
            });

            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateQuantity(true);
                }
            });

            mRemoveImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateQuantity(false);
                }
            });
        }

        public void bindView(VendorItem vendorItem) {
            Glide.with(mView).load(vendorItem.getUrl()).into(mItemImageView);
            mItemNameTextView.setText(vendorItem.getName() + " (" + vendorItem.getUnit() + ")");
            mItemPriceTextView.setText("Rs. " + vendorItem.getUnitPrice());
            mItemQuantity = vendorItem.getOrderCount();
            updateUI();
        }

        private void updateQuantity(boolean add) {
            if (add) {
                mItemQuantity += 1;
            } else if (mItemQuantity > 0){
                mItemQuantity -= 1;
            }
            updateUI();
            mVendorItemCountListener.onVendorItemCountChangeListener(getAdapterPosition(), mItemQuantity);
        }

        private void updateUI() {
            mItemQuantityTextView.setText(mItemQuantity + "");
            if (mItemQuantity > 0) {
                mAddButton.setVisibility(View.GONE);
            } else {
                mAddButton.setVisibility(View.VISIBLE);
            }
        }
    }
}
