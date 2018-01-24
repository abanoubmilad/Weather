package werpx.weather;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import werpx.weather.data.CitiesWrapper;
import werpx.weather.data.CityWeather;
import werpx.weather.data.Forecast;
import werpx.weather.data.ForecastWrapper;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class TestingPersistence {

    private Context instrumentationContext;
    private Realm realm;

    @Before
    public void setup() {
        instrumentationContext = InstrumentationRegistry.getContext();
        realm = Realm.getInstance(instrumentationContext);
    }

    @Test
    public void testCitiesWrapper_test1_clearing() {
        // lets clear all objects of type CitiesWrapper
        realm.beginTransaction();
        realm.clear(CitiesWrapper.class);
        realm.commitTransaction();

        RealmResults<CitiesWrapper> result = realm.where(CitiesWrapper.class).findAll();
        assertThat(result.size(), is(0));

    }

    @Test
    public void testCitiesWrapper_test2_saving() {
        long lastUpdated = Utility.getCurrentTimeStamp();
        ArrayList<CityWeather> cities = new ArrayList<>();
        cities.add(new CityWeather(1, "Alexandria", "20"));
        cities.add(new CityWeather(2, "Cairo", "60"));
        cities.add(new CityWeather(3, "Luxor", "90"));

        CitiesWrapper wrapper = new CitiesWrapper(lastUpdated, cities);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(wrapper);
        realm.commitTransaction();

        RealmResults<CitiesWrapper> result = realm.where(CitiesWrapper.class).findAll();
        assertThat(result.size(), is(1));

        CitiesWrapper retrievedWrapper = result.get(0);
        assertThat(retrievedWrapper.getLastUpdated(), is(lastUpdated));
        assertThat(retrievedWrapper.getCities().size(), is(cities.size()));

        for (int i = 0; i < cities.size(); i++) {
            assertThat(retrievedWrapper.getCities().get(i).getId(), is(cities.get(i).getId()));
            assertThat(retrievedWrapper.getCities().get(i).getName(), is(cities.get(i).getName()));
            assertThat(retrievedWrapper.getCities().get(i).getTemp(), is(cities.get(i).getTemp()));
        }


    }


    @Test
    public void testCitiesWrapper_test3_multiple_savings() {
        long lastUpdated = Utility.getCurrentTimeStamp();
        ArrayList<CityWeather> cities = new ArrayList<>();
        cities.add(new CityWeather(12, "Aswan", "1"));
        cities.add(new CityWeather(6, "Port Said", "5"));

        CitiesWrapper wrapper = new CitiesWrapper(lastUpdated, cities);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(wrapper);
        realm.commitTransaction();

        RealmResults<CitiesWrapper> result = realm.where(CitiesWrapper.class).findAll();
        assertThat(result.size(), is(1));

        CitiesWrapper retrievedWrapper = result.get(0);
        assertThat(retrievedWrapper.getLastUpdated(), is(lastUpdated));
        assertThat(retrievedWrapper.getCities().size(), is(cities.size()));

        for (int i = 0; i < cities.size(); i++) {
            assertThat(retrievedWrapper.getCities().get(i).getId(), is(cities.get(i).getId()));
            assertThat(retrievedWrapper.getCities().get(i).getName(), is(cities.get(i).getName()));
            assertThat(retrievedWrapper.getCities().get(i).getTemp(), is(cities.get(i).getTemp()));
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(wrapper);
        realm.commitTransaction();

        result = realm.where(CitiesWrapper.class).findAll();
        assertThat(result.size(), is(1));


    }

    @Test
    public void testForecastWrapper_test1_clearing() {
        // lets clear all objects of type ForecastWrapper
        realm.beginTransaction();
        realm.clear(ForecastWrapper.class);
        realm.commitTransaction();

        RealmResults<ForecastWrapper> result = realm.where(ForecastWrapper.class).findAll();
        assertThat(result.size(), is(0));

    }

    @Test
    public void testForecastWrapperWrapper_test2_saving() {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        forecasts.add(new Forecast(Utility.getCurrentTimeStamp(), 15, 20, 40));
        forecasts.add(new Forecast(Utility.getCurrentTimeStamp(), 19, 23, 30));
        forecasts.add(new Forecast(Utility.getCurrentTimeStamp(), 60, 10, 29));
        forecasts.add(new Forecast(Utility.getCurrentTimeStamp(), 16, 9, 17));
        long lastUpdated = Utility.getCurrentTimeStamp();
        int cityId = 133515;

        ForecastWrapper wrapper = new ForecastWrapper(lastUpdated, cityId, forecasts);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(wrapper);
        realm.commitTransaction();

        RealmResults<ForecastWrapper> result = realm.where(ForecastWrapper.class).equalTo("cityId", 133515).findAll();
        assertThat(result.size(), is(1));

        ForecastWrapper retrievedWrapper = result.get(0);
        assertThat(retrievedWrapper.getLastUpdated(), is(lastUpdated));
        assertThat(retrievedWrapper.getCityID(), is(cityId));
        assertThat(retrievedWrapper.getForecasts().size(), is(forecasts.size()));

        for (int i = 0; i < forecasts.size(); i++) {
            assertThat(retrievedWrapper.getForecasts().get(i).getDayTemp(), is(forecasts.get(i).getDayTemp()));
            assertThat(retrievedWrapper.getForecasts().get(i).getMaxTemp(), is(forecasts.get(i).getMaxTemp()));
            assertThat(retrievedWrapper.getForecasts().get(i).getMinTemp(), is(forecasts.get(i).getMinTemp()));
            assertThat(retrievedWrapper.getForecasts().get(i).getTimeStamp(), is(forecasts.get(i).getTimeStamp()));
        }


    }


    @Test
    public void testForecastWrapper_test3_multiple_savings() {
        ArrayList<Forecast> forecasts = new ArrayList<>();
        forecasts.add(new Forecast(Utility.getCurrentTimeStamp(), 20, 15, 122));
        forecasts.add(new Forecast(Utility.getCurrentTimeStamp(), 123, 13, 160));
        long lastUpdated = Utility.getCurrentTimeStamp();
        int cityId = 133515;


        ForecastWrapper wrapper = new ForecastWrapper(lastUpdated, cityId, forecasts);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(wrapper);
        realm.commitTransaction();

        RealmResults<ForecastWrapper> result = realm.where(ForecastWrapper.class).equalTo("cityId", 133515).findAll();
        assertThat(result.size(), is(1));

        ForecastWrapper retrievedWrapper = result.get(0);
        assertThat(retrievedWrapper.getLastUpdated(), is(lastUpdated));
        assertThat(retrievedWrapper.getCityID(), is(cityId));
        assertThat(retrievedWrapper.getForecasts().size(), is(forecasts.size()));

        for (int i = 0; i < forecasts.size(); i++) {
            assertThat(retrievedWrapper.getForecasts().get(i).getDayTemp(), is(forecasts.get(i).getDayTemp()));
            assertThat(retrievedWrapper.getForecasts().get(i).getMaxTemp(), is(forecasts.get(i).getMaxTemp()));
            assertThat(retrievedWrapper.getForecasts().get(i).getMinTemp(), is(forecasts.get(i).getMinTemp()));
            assertThat(retrievedWrapper.getForecasts().get(i).getTimeStamp(), is(forecasts.get(i).getTimeStamp()));
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(wrapper);
        realm.commitTransaction();

        result = realm.where(ForecastWrapper.class).equalTo("cityId", 133515).findAll();
        assertThat(result.size(), is(1));

        result = realm.where(ForecastWrapper.class).notEqualTo("cityId", 133515).findAll();
        assertThat(result.size(), is(0));

    }
}