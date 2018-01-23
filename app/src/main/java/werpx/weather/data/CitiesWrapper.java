package werpx.weather.data;


import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

public class CitiesWrapper extends RealmObject {
    private int lastUpdated;
    private RealmList<CityWeather> cities;

    public CitiesWrapper() {
    }

    public CitiesWrapper(int lastUpdated, RealmList<CityWeather> cities) {
        this.lastUpdated = lastUpdated;
        this.cities = cities;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public RealmList<CityWeather> getCities() {
        return cities;
    }

    public void setCities(RealmList<CityWeather> cities) {
        this.cities = cities;
    }
}
