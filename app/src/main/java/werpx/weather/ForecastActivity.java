package werpx.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import werpx.weather.adapter.ForecastAdapter;
import werpx.weather.data.Forecast;
import werpx.weather.data.ForecastWrapper;
import werpx.weather.data.Intractor;


public class ForecastActivity extends ActionBarActivity {
    public static final String EXTRA_ARGUMENT_NAME = "name";
    public static final String EXTRA_ARGUMENT_ID = "id";
    private ForecastAdapter adapter;
    private int currentCityID;
    private String currentCityName;
    private TextView lastUpdate;
    private SwipeRefreshLayout swipe;

    private View noInternetMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lastUpdate = (TextView) findViewById(R.id.last_update);
        lastUpdate.setText("last update: ");

        noInternetMessage = findViewById(R.id.no_internet);

        adapter = new ForecastAdapter(getApplicationContext(), new ArrayList<Forecast>());
        ListView lv = (ListView) findViewById(R.id.forecast_list);
        lv.setAdapter(adapter);

        Intent intent = getIntent();
        currentCityID = intent.getIntExtra(EXTRA_ARGUMENT_ID, -1);
        currentCityName = intent.getStringExtra(EXTRA_ARGUMENT_NAME);

        ((TextView) findViewById(R.id.city_name)).setText(currentCityName);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (Utility.isNetworkAvailable(getApplicationContext())) {
                            requestDataLive();
                        } else {
                            synchronized (ForecastActivity.class) {
                                swipe.setRefreshing(false);
                            }
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

    private void requestDataOffline() {
        swipe.setRefreshing(true);
        Intractor.getCityForecastOfflineMode(getApplicationContext(), currentCityID, new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (ForecastActivity.class) {
                            noInternetMessage.setVisibility(View.VISIBLE);
                            swipe.setRefreshing(false);
                        }
                    }
                });
            }

            @Override
            public void onSuccess(final Object result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (ForecastActivity.class) {
                            noInternetMessage.setVisibility(View.GONE);
                            ForecastWrapper wrapper = (ForecastWrapper) result;
                            lastUpdate.setText("last update: " + new SimpleDateFormat(Utility.LAST_UPDATED_DATE_FORMAT).format(new Date(wrapper.getLastUpdated())));
                            adapter.clearThenAddAll(Utility.extractForecastsArrayList(wrapper.getForecasts()));
                            swipe.setRefreshing(false);
                        }
                    }
                });
            }
        });
    }

    private void requestDataLive() {
        swipe.setRefreshing(true);
        Intractor.getCityForecast(getApplicationContext(), currentCityID, new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Cant get updated forecast!", Toast.LENGTH_SHORT).show();
                        requestDataOffline();
                    }
                });
            }

            @Override
            public void onSuccess(final Object result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (ForecastActivity.class) {
                            noInternetMessage.setVisibility(View.GONE);
                            ForecastWrapper wrapper = (ForecastWrapper) result;
                            lastUpdate.setText("last update: " + new SimpleDateFormat(Utility.LAST_UPDATED_DATE_FORMAT).format(new Date(wrapper.getLastUpdated())));
                            adapter.clearThenAddAll(Utility.extractForecastsArrayList(wrapper.getForecasts()));
                            swipe.setRefreshing(false);
                        }
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
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
