package werpx.weather.data;


import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CitiesWrapper extends RealmObject {
    @PrimaryKey
    private int citiesWrapperID=0;
    private long lastUpdated;
    private RealmList<CityWeather> cities;

    public CitiesWrapper() {
    }

    public CitiesWrapper(long lastUpdated, RealmList<CityWeather> cities) {
        this.lastUpdated = lastUpdated;
        this.cities = cities;
    }
    public CitiesWrapper(long lastUpdated, ArrayList<CityWeather> cities) {
        this.lastUpdated = lastUpdated;
        this.cities = new RealmList<>();
        for(CityWeather cityWeather : cities){
            this.cities.add(cityWeather);
        }
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

//    public RealmList<CityWeather> getCities() {
//        return cities;
//    }
    public ArrayList<CityWeather> getCities() {
        ArrayList<CityWeather> toReturn = new ArrayList<>(cities.size());
        for(CityWeather cityWeather : cities){
            toReturn.add(cityWeather);
        }
        return toReturn;
    }

    public void setCities(RealmList<CityWeather> cities) {
        this.cities = cities;
    }

    public int getCitiesWrapperID() {
        return citiesWrapperID;
    }

    public void setCitiesWrapperID(int citiesWrapperID) {
        this.citiesWrapperID = citiesWrapperID;
    }
}
