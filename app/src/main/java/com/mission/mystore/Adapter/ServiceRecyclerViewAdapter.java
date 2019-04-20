package com.mission.mystore.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mission.mystore.R;
import com.mission.mystore.Service;

import java.util.ArrayList;
import java.util.Arrays;

public class ServiceRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Service> mServices;
    private ServiceClickListener mListener;

    public interface ServiceClickListener {
        void onServiceItemClickListener(int pos);
    }

    public ServiceRecyclerViewAdapter(ArrayList<Service> services, ServiceClickListener listener) {
        mServices = services;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View serviceView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_service, viewGroup, false);
        return new ServiceViewHolder(serviceView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ServiceViewHolder)viewHolder).bindView(mServices.get(i));
    }

    @Override
    public int getItemCount() {
        return mServices.size();
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
                    mListener.onServiceItemClickListener(getAdapterPosition());
                }
            });
        }

        public void bindView(Service service) {
            mServiceImageView.setImageResource(service.getIcon());
            mServiceTextView.setText(service.getName());
        }
    }
}
