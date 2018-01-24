package werpx.weather.data;


import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import werpx.weather.CustomCallback;
import werpx.weather.Utility;
import werpx.weather.network.WeatherAPI;

public class Intractor {

    public static void getCitiesWeatherOfflineMode(Context context, final CustomCallback viewControllerCallback) {
        Realm realm = Realm.getInstance(context);

        CitiesWrapper wrapper = realm.where(CitiesWrapper.class).equalTo("citiesWrapperID", 0).findFirst();

        realm.close();

        if (wrapper == null)
            viewControllerCallback.onFailure("");
        else
            viewControllerCallback.onSuccess(wrapper);

    }

    public static void getCityForecastOfflineMode(final Context context, int cityID, final CustomCallback viewControllerCallback) {
        Realm realm = Realm.getInstance(context);

        ForecastWrapper wrapper = realm.where(ForecastWrapper.class).equalTo("cityID", cityID).findFirst();

        realm.close();

        if (wrapper == null)
            viewControllerCallback.onFailure("");
        else
            viewControllerCallback.onSuccess(wrapper);

    }

    public static void getCitiesWeather(final Context context, final CustomCallback viewControllerCallback) {
        WeatherAPI.getCitiesWeather(new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                viewControllerCallback.onFailure(failureMessage);
            }

            @Override
            public void onSuccess(Object result) {
                ArrayList<CityWeather> cities = APIJsonParser.getCitiesWeather((String) result);
                if (cities == null)
                    viewControllerCallback.onFailure("Error, Could not perform action!");
                else {
                    CitiesWrapper wrapper = new CitiesWrapper(Utility.getCurrentTimeStamp(), cities);
                    Realm realm = Realm.getInstance(context);
                    // Persist your data in a transaction
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(wrapper);
                    realm.commitTransaction();

                    viewControllerCallback.onSuccess(cities);
                }
            }
        });

    }

    public static void getCityForecast(final Context context, final int cityID, final CustomCallback viewControllerCallback) {
        WeatherAPI.getCityForecast(cityID, new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                viewControllerCallback.onFailure(failureMessage);
            }

            @Override
            public void onSuccess(Object result) {
                ArrayList<Forecast> forecasts = APIJsonParser.getCityForecast((String) result);
                if (forecasts == null)
                    viewControllerCallback.onFailure("Error, Could not perform action!");
                else {
                    ForecastWrapper wrapper = new ForecastWrapper(Utility.getCurrentTimeStamp(), cityID, forecasts);
                    Realm realm = Realm.getInstance(context);
                    // Persist your data in a transaction
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(wrapper);
                    realm.commitTransaction();

                    viewControllerCallback.onSuccess(forecasts);
                }

            }
        });

    }
}
