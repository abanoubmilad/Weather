package werpx.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import werpx.weather.adapter.ForecastAdapter;
import werpx.weather.data.Forecast;
import werpx.weather.data.Intractor;


public class ForecastActivity extends ActionBarActivity {
    public static final String EXTRA_ARGUMENT_NAME="name";
    public static final String EXTRA_ARGUMENT_ID="id";
    private ForecastAdapter adapter;
    private int currentCityID;
    private String currentCityName;

    private View noInternetMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        noInternetMessage = findViewById(R.id.no_internet);
        if(!Utility.isNetworkAvailable(getApplicationContext()))
            noInternetMessage.setVisibility(View.VISIBLE);
        noInternetMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utility.isNetworkAvailable(getApplicationContext())) {
                    noInternetMessage.setVisibility(View.GONE);
                }
            }
        });

        adapter = new ForecastAdapter(getApplicationContext(), new ArrayList<Forecast>());
        ListView lv = (ListView) findViewById(R.id.forecast_list);
        lv.setAdapter(adapter);

        Intent intent = getIntent();
        currentCityID=intent.getIntExtra(EXTRA_ARGUMENT_ID,-1);
        currentCityName=intent.getStringExtra(EXTRA_ARGUMENT_NAME);

        ((TextView) findViewById(R.id.city_name)).setText(currentCityName);

        Intractor.getCityForecast(currentCityID, new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                    }
                });
            }

            @Override
            public void onSuccess(final Object result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll((ArrayList<Forecast>) result);

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
