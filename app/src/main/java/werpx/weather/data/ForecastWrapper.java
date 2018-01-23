package werpx.weather.data;


import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ForecastWrapper extends RealmObject {
    private int lastUpdated;
    private int cityID;
    private RealmList<Forecast> forecasts;

    public ForecastWrapper() {
    }

    public ForecastWrapper(int lastUpdated, int cityID, RealmList<Forecast> forecasts) {
        this.lastUpdated = lastUpdated;
        this.cityID = cityID;
        this.forecasts = forecasts;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public RealmList<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(RealmList<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
