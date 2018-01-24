package werpx.weather.network;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import werpx.weather.BuildConfig;
import werpx.weather.CustomCallback;


public class WeatherAPI {
    private final static String API_URL = "http://api.openweathermap.org/data/2.5";

    private final static String API_KEY_PARAMETER = "APPID";
    private final static String API_CITY_ID_PARAMETER = "id";
    private final static String API_CITY_FORECAST_COUNT_PARAMETER = "cnt";
    private final static String API_UNITS_PARAMETER = "units";

    private final static String CITIES = "361058,6618607,5128638,360502,2911298";

    private static Request prepareCityForecastRequest(int cityID) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL + "/forecast/daily").newBuilder();
        urlBuilder.addQueryParameter(API_KEY_PARAMETER, BuildConfig.openweathermap_api_key);
        urlBuilder.addQueryParameter(API_UNITS_PARAMETER, "metric");
        urlBuilder.addQueryParameter(API_CITY_ID_PARAMETER, cityID + "");
        urlBuilder.addQueryParameter(API_CITY_FORECAST_COUNT_PARAMETER, "5");
        String url = urlBuilder.build().toString();

        return new Request.Builder()
                .url(url)
                .build();
    }

    public static void getCityForecast(int cityID, final CustomCallback customCallback) {
        HttpClient.getInstance().newCall(prepareCityForecastRequest(cityID)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                customCallback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    customCallback.onFailure(response.toString());
                } else {
                    response.toString();
                    customCallback.onSuccess(response.body().string());
                }

            }

        });
    }

    public static Response getCityForecast(int cityID) throws IOException {
        return HttpClient.getInstance().newCall(prepareCityForecastRequest(cityID)).execute();
    }

    private static Request prepareCitiesWeatherRequest() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL + "/group").newBuilder();
        urlBuilder.addQueryParameter(API_KEY_PARAMETER, BuildConfig.openweathermap_api_key);
        urlBuilder.addQueryParameter(API_UNITS_PARAMETER, "metric");
        urlBuilder.addQueryParameter(API_CITY_ID_PARAMETER, CITIES);
        String url = urlBuilder.build().toString();

        return new Request.Builder()
                .url(url)
                .build();
    }

    public static void getCitiesWeather(final CustomCallback customCallback) {

        HttpClient.getInstance().newCall(prepareCitiesWeatherRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                customCallback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    customCallback.onFailure(response.toString());
                } else {
                    customCallback.onSuccess(response.body().string());
                }

            }

        });
    }

    public static Response getCitiesWeather() throws IOException {
        return HttpClient.getInstance().newCall(prepareCitiesWeatherRequest()).execute();
    }
}