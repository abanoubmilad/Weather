package werpx.weather.data;


import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ForecastWrapper extends RealmObject {
    private long lastUpdated;
    @PrimaryKey
    private int cityID;
    private RealmList<Forecast> forecasts;

    public ForecastWrapper() {
    }

    public ForecastWrapper(long lastUpdated, int cityID, RealmList<Forecast> forecasts) {
        this.lastUpdated = lastUpdated;
        this.cityID = cityID;
        this.forecasts = forecasts;
    }
    public ForecastWrapper(long lastUpdated, int cityID, ArrayList<Forecast> forecasts) {
        this.lastUpdated = lastUpdated;
        this.cityID = cityID;
        this.forecasts = new RealmList<>();
        for(Forecast forecast : forecasts){
            this.forecasts.add(forecast);
        }
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

//    public RealmList<Forecast> getForecasts() {
//        return forecasts;
//    }
    public ArrayList<Forecast> getForecasts() {
        ArrayList<Forecast> toReturn = new ArrayList<>(forecasts.size());
        for(Forecast forecast : forecasts){
            toReturn.add(forecast);
        }
        return toReturn;
    }

    public void setForecasts(RealmList<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
