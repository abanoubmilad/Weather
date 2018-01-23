package werpx.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class APIJsonParser {

    public static ArrayList<CityWeather> getCitiesWeather(String response) {
        if (response == null)
            return null;
        ArrayList<CityWeather> result = new ArrayList<>();
        try {
            JSONObject parentObj = new JSONObject(response);
            JSONArray citiesArray = parentObj.getJSONArray("list");
            for (int i = 0; i < citiesArray.length(); i++) {
                JSONObject obj = citiesArray.getJSONObject(i);
                result.add(new CityWeather(obj.getInt("id"), obj.getString("name"), obj.getJSONObject("main").getString("temp")));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Forecast> getCityForecast(String response) {
        if (response == null)
            return null;
        ArrayList<Forecast> result = new ArrayList<>();
        try {
            JSONObject parentObj = new JSONObject(response);
            JSONArray forecastArray = parentObj.getJSONArray("list");
            for (int i = 0; i < forecastArray.length(); i++) {
                JSONObject obj = forecastArray.getJSONObject(i);
                JSONObject tempObj = obj.getJSONObject("temp");
                result.add(new Forecast(obj.getLong("dt"),
                        tempObj.getDouble("day"),
                        tempObj.getDouble("min"), tempObj.getDouble("max")));
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
