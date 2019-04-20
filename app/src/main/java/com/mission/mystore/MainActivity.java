package com.mission.mystore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mission.mystore.Adapter.ServiceRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ServiceRecyclerViewAdapter.ServiceClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    ServiceRecyclerViewAdapter mServiceRecyclerViewAdapter;
    ArrayList<Service> mServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mServices = getServiceList();
        RecyclerView serviceRecyclerView = findViewById(R.id.rv_services);
        serviceRecyclerView.setHasFixedSize(true);
        serviceRecyclerView.setLayoutManager(new GridLayoutManager(this,
                Utility.getNumOfColumns(this,
                        getResources().getDimension(R.dimen.width_service_item))));
        mServiceRecyclerViewAdapter = new ServiceRecyclerViewAdapter(mServices, this);
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
    public void onServiceItemClickListener(int pos) {
        String service = mServices.get(pos).getName();
        if (service.equals(getResources().getString(R.string.grocery))) {
        }
    }

    private ArrayList<Service> getServiceList() {
        ArrayList<Service> services = new ArrayList<>();
        services.add(new Service(getResources().getString(R.string.grocery), R.drawable.grocery));
        services.add(new Service(getResources().getString(R.string.medicine), R.drawable.medicine));
        services.add(new Service(getResources().getString(R.string.vegetables), R.drawable.vegetables));
        services.add(new Service(getResources().getString(R.string.fruits), R.drawable.fruits));
        services.add(new Service(getResources().getString(R.string.organic_food), R.drawable.organic_food));
        services.add(new Service(getResources().getString(R.string.poultry), R.drawable.poultry));
        services.add(new Service(getResources().getString(R.string.laundry), R.drawable.laundry));
        services.add(new Service(getResources().getString(R.string.tiffins_snacks), R.drawable.tiffins));
        services.add(new Service(getResources().getString(R.string.catering), R.drawable.catering));
        services.add(new Service(getResources().getString(R.string.electrical_appliances), R.drawable.electrical_repair));
        services.add(new Service(getResources().getString(R.string.vehicle_mechanic), R.drawable.vehicle_mechanic));
        services.add(new Service(getResources().getString(R.string.pest_control), R.drawable.pest_control));
        services.add(new Service(getResources().getString(R.string.mesh_service), R.drawable.mesh));
        services.add(new Service(getResources().getString(R.string.carpenters), R.drawable.painter_carpenter));
        services.add(new Service(getResources().getString(R.string.maid), R.drawable.ic_launcher_foreground));

        return services;
    }
}
