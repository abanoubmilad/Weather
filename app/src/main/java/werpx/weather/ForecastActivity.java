package werpx.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ForecastAdapter(getApplicationContext(), new ArrayList<Forecast>());
        ListView lv = (ListView) findViewById(R.id.forecast_list);
        lv.setAdapter(adapter);

        Intent intent = getIntent();
        currentCityID=intent.getIntExtra(EXTRA_ARGUMENT_ID,-1);
        currentCityName=intent.getStringExtra(EXTRA_ARGUMENT_NAME);


        Intractor.getCityForecast(currentCityID, new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {

            }

            @Override
            public void onSuccess(Object result) {
                adapter.addAll((ArrayList<Forecast>) result);

            }
        });
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
}
