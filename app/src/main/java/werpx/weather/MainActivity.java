package werpx.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import werpx.weather.adapter.CityWeatherAdapter;
import werpx.weather.data.CityWeather;
import werpx.weather.data.Intractor;


public class MainActivity extends ActionBarActivity {
    private CityWeatherAdapter adapter;
    private View noInternetMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Intractor.getCitiesWeather(new CustomCallback() {
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

                        adapter.addAll((ArrayList<CityWeather>) result);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
