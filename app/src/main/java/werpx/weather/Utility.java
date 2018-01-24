package werpx.weather;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.util.ArrayList;

import io.realm.RealmList;
import werpx.weather.data.CityWeather;
import werpx.weather.data.Forecast;

public class Utility {
    public static final String FORECAST_DATE_FORMAT = "EEE yyyy/MM/dd";
    public static final String LAST_UPDATED_DATE_FORMAT = "EEE yyyy/MM/dd HH:mm";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void openContactUsWebPage(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://werpx.com/en/contact"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    public static ArrayList<CityWeather> extractCitiesArrayList(RealmList<CityWeather> list) {
        ArrayList<CityWeather> toReturn = new ArrayList<>(list.size());
        for(CityWeather cityWeather : list){
            toReturn.add(cityWeather);
        }
        return toReturn;
    }
    public static ArrayList<Forecast> extractForecastsArrayList(RealmList<Forecast> list ) {
        ArrayList<Forecast> toReturn = new ArrayList<>(list.size());
        for(Forecast forecast : list){
            toReturn.add(forecast);
        }
        return toReturn;
    }


}
