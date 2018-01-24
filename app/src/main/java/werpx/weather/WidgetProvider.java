package werpx.weather;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;

import werpx.weather.data.CitiesWrapper;
import werpx.weather.data.CityWeather;
import werpx.weather.data.Intractor;

public class WidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {

            final int appWidgetId = appWidgetIds[0];

            final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

            Intractor.getCitiesWeather(context, new CustomCallback() {
                @Override
                public void onFailure(String failureMessage) {

                }

                @Override
                public void onSuccess(Object result) {
                    ArrayList<CityWeather> cities = Utility.extractCitiesArrayList(((CitiesWrapper) result).getCities());
                    if (cities.size() == 0)
                        return;
                    views.setTextViewText(R.id.city_name, cities.get(0).getName());
                    views.setTextViewText(R.id.city_temp, cities.get(0).getTemp());
                    appWidgetManager.updateAppWidget(appWidgetId, views);

                }
            });

    }
}