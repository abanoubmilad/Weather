package werpx.weather;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;
import werpx.weather.data.APIJsonParser;
import werpx.weather.data.CityWeather;
import werpx.weather.data.Forecast;
import werpx.weather.network.WeatherAPI;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestingAPIParsing {

    @Test
    public void testCities() {
        try {
            Response response = WeatherAPI.getCitiesWeather();
            if (response.isSuccessful()) {
                String json = response.body().string();
                ArrayList<CityWeather> array = APIJsonParser.getCitiesWeather(json);
                assertThat(array, is(notNullValue()));
                assertThat(array.size(), is(5));

                for (int i = 0; i < array.size(); i++) {
                    assertThat(array.get(i).getId(), is(not(-1)));
                    assertThat(array.get(i).getName(), is(notNullValue()));
                    assertThat(array.get(i).getTemp(), is(notNullValue()));
                }

            }
        } catch (IOException e) {
        }

    }
    @Test
    public void testForecast() {
        try {
            Response response = WeatherAPI.getCityForecast(361058);
            if (response.isSuccessful()) {
                String json = response.body().string();
                ArrayList<Forecast> array = APIJsonParser.getCityForecast(json);
                assertThat(array, is(notNullValue()));
                assertThat(array.size(), is(5));

                 }
        } catch (IOException e) {
        }

    }
}