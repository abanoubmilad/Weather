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
import werpx.weather.data.Forecast;
import werpx.weather.data.Intractor;


public class MainActivity extends ActionBarActivity {
    private CityWeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new CityWeatherAdapter(getApplicationContext(), new ArrayList<CityWeather>());
        ListView lv = (ListView) findViewById(R.id.cities_list);
        lv.setAdapter(adapter);

        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CityWeather cityWeather = adapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(), ForecastActivity.class);
                intent.putExtra(ForecastActivity.EXTRA_ARGUMENT_NAME, cityWeather.getName());
                intent.putExtra(ForecastActivity.EXTRA_ARGUMENT_ID, cityWeather.getId());
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Intractor.getCitiesWeather(new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {

            }

            @Override
            public void onSuccess(Object result) {

                adapter.addAll((ArrayList<CityWeather>) result);
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
