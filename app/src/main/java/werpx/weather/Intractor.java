package werpx.weather;


import java.util.ArrayList;

public class Intractor {
    public static void getCitiesWeather(final CustomCallback viewControllerCallback) {
        WeatherAPI.getCitiesWeather(new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                viewControllerCallback.onFailure(failureMessage);
            }

            @Override
            public void onSuccess(Object result) {
                ArrayList<CityWeather> cities = APIJsonParser.getCitiesWeather((String) result);
                if(cities==null)
                    viewControllerCallback.onFailure("Error, Could not perform action!");
                else
                    viewControllerCallback.onSuccess(cities);
            }
        });

    }

    public static void getCityForecast(int  cityID, final CustomCallback viewControllerCallback) {
        WeatherAPI.getCityForecast(cityID, new CustomCallback() {
            @Override
            public void onFailure(String failureMessage) {
                viewControllerCallback.onFailure(failureMessage);
            }

            @Override
            public void onSuccess(Object result) {
                ArrayList<Forecast> forecast = APIJsonParser.getCityForecast((String) result);
                if(forecast==null)
                    viewControllerCallback.onFailure("Error, Could not perform action!");
                else
                viewControllerCallback.onSuccess(forecast);

            }
        });

    }
}
