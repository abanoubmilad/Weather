package werpx.weather.data;


import java.util.ArrayList;

import io.realm.RealmObject;

public class CitiesWrapper extends RealmObject {
    private int lastUpdated;
    private ArrayList<CityWeather> cities;

    public CitiesWrapper() {
    }

    public CitiesWrapper(int lastUpdated, ArrayList<CityWeather> cities) {
        this.lastUpdated = lastUpdated;
        this.cities = cities;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ArrayList<CityWeather> getCities() {
        return cities;
    }

    public void setCities(ArrayList<CityWeather> cities) {
        this.cities = cities;
    }
}
