package werpx.weather;

import okhttp3.HttpUrl;
import okhttp3.Request;


public class WeatherAPI {
    private final static String API_URL="http://api.openweathermap.org/data/2.5/weather";
    private final static String API_KEY_PARAMETER="APPID";
    private final static String API_CITY_ID_PARAMETER="id";
    private final static String [] cities= new String []{"361058","6618607","5128638","360502","2911298"};

    public static void getCityWeather(String cityID){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
        urlBuilder.addQueryParameter(API_KEY_PARAMETER, "");
        urlBuilder.addQueryParameter(API_CITY_ID_PARAMETER, cityID);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
    }

}
