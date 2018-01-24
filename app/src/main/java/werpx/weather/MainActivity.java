package werpx.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import werpx.weather.adapter.CityWeatherAdapter;
import werpx.weather.data.CitiesWrapper;
import werpx.weather.data.CityWeather;
import werpx.weather.data.Intractor;


public class MainActivity extends ActionBarActivity {
    private CityWeatherAdapter adapter;
    private View noInternetMessage;
    private TextView lastUpdate;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastUpdate = (TextView) findViewById(R.id.last_update);
        lastUpdate.setText("last update: ");

        noInternetMessage = findViewById(R.id.no_internet);

        adapter = new CityWeatherAdapter(getApplicationContext(), new ArrayList<CityWeather>());
        ListView lv = (ListView) findViewById(R.id.cities_list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CityWeather cityWeather = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), ForecastActivity.class);
                intent.putExtra(ForecastActivity.EXTRA_ARGUMENT_NAME, cityWeather.getName());
                intent.putExtra(ForecastActivity.EXTRA_ARGUMENT_ID, cityWeather.getId());
                startActivity(intent);
            }
        });

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (Utility.isNetworkAvailable(getApplicationContext())) {
                            requestDataLive();
                        }
                    }
                }
        );
        noInternetMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utility.isNetworkAvailable(getApplicationContext())) {
                    requestDataLive();
                }
            }
        });
        if (Utility.isNetworkAvailable(getApplicationContext()))
            requestDataLive();
        else
            requestDataOffline();

    }

    private synchronized void requestDataOffline() {
        Intractor.getCitiesWeatherOfflineMode(getApplicationContext(), new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noInternetMessage.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onSuccess(final Object result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noInternetMessage.setVisibility(View.GONE);
                        CitiesWrapper wrapper = (CitiesWrapper) result;
                        lastUpdate.setText("last update: " + new SimpleDateFormat(Utility.LAST_UPDATED_DATE_FORMAT).format(new Date(wrapper.getLastUpdated())));
                        adapter.clearThenAddAll(Utility.extractCitiesArrayList(wrapper.getCities()));

                    }
                });
            }
        });
    }

    private synchronized void requestDataLive() {
        Intractor.getCitiesWeather(getApplicationContext(), new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Cant get updated weather!",Toast.LENGTH_SHORT).show();
                        requestDataOffline();
                    }
                });
            }

            @Override
            public void onSuccess(final Object result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noInternetMessage.setVisibility(View.GONE);
                        CitiesWrapper wrapper = (CitiesWrapper) result;
                        lastUpdate.setText("last update: " + new SimpleDateFormat(Utility.LAST_UPDATED_DATE_FORMAT).format(new Date(wrapper.getLastUpdated())));
                        adapter.clearThenAddAll(Utility.extractCitiesArrayList(wrapper.getCities()));
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_contact_us) {
            Utility.openContactUsWebPage(getApplicationContext());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
