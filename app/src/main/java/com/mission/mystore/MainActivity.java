package com.mission.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mission.mystore.Adapter.ServiceRecyclerViewAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.mission.mystore.ServiceActivity.KEY_TYPE_SERVICE_ORDER;

public class MainActivity extends AppCompatActivity implements ServiceRecyclerViewAdapter.ServiceClickListener {

    public static final int REQUEST_SERVICE_ORDER = 1;
    private String TAG = MainActivity.class.getSimpleName();
    ServiceRecyclerViewAdapter mServiceRecyclerViewAdapter;
    ArrayList<Service> mServiceList;
    private Map<String, ServiceOrder> mServiceOrderMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mServiceList = getServiceList();
        mServiceOrderMap = new HashMap<>();
        RecyclerView serviceRecyclerView = findViewById(R.id.rv_services);
        serviceRecyclerView.setHasFixedSize(true);
        serviceRecyclerView.setLayoutManager(new GridLayoutManager(this,
                Utility.getNumOfColumns(this,
                        getResources().getDimension(R.dimen.width_service_item))));
        mServiceRecyclerViewAdapter = new ServiceRecyclerViewAdapter(mServiceList, this);
        serviceRecyclerView.setAdapter(mServiceRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceItemClickListener(int serviceIndex) {
        Gson gson = new Gson();
        String serviceName = mServiceList.get(serviceIndex).getName();
        if (serviceName.equals(getResources().getString(R.string.grocery))) {
            Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
            if (mServiceOrderMap.containsKey(serviceName)) {
                intent.putExtra(KEY_TYPE_SERVICE_ORDER, gson.toJson(mServiceOrderMap.get(serviceName)));
            }
            intent.putExtra(ServiceActivity.KEY_TYPE_SERVICE, serviceName);
            startActivityForResult(intent, REQUEST_SERVICE_ORDER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_SERVICE_ORDER: {
                if (resultCode == RESULT_OK) {
                    if (data != null &&
                            data.hasExtra(KEY_TYPE_SERVICE_ORDER)) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ServiceOrder>() {}.getType();
                        ServiceOrder remoteServiceOrder =
                                gson.fromJson(data.getStringExtra(KEY_TYPE_SERVICE_ORDER) ,type);
                        mServiceOrderMap.put(remoteServiceOrder.getServiceName(), remoteServiceOrder);
                    }
                }
                break;
            }
        }
    }

    private ArrayList<Service> getServiceList() {
        ArrayList<Service> services = new ArrayList<>();
        services.add(new Service(getResources().getString(R.string.grocery), R.drawable.grocery_image));
        services.add(new Service(getResources().getString(R.string.medicine), R.drawable.medicine_image));
        services.add(new Service(getResources().getString(R.string.vegetables), R.drawable.vegetable_image));
        services.add(new Service(getResources().getString(R.string.fruits), R.drawable.fruit_image));
        services.add(new Service(getResources().getString(R.string.organic_food), R.drawable.organic_food_image));
        services.add(new Service(getResources().getString(R.string.poultry), R.drawable.poultry_image));
        services.add(new Service(getResources().getString(R.string.laundry), R.drawable.laundry_image));
        services.add(new Service(getResources().getString(R.string.tiffins_snacks), R.drawable.tiffins_image));
        services.add(new Service(getResources().getString(R.string.catering), R.drawable.catering_image));
        services.add(new Service(getResources().getString(R.string.vehicle_mechanic), R.drawable.vehicle_mechanic_image));
        services.add(new Service(getResources().getString(R.string.pest_control), R.drawable.pest_control_image));

        return services;
    }
}
