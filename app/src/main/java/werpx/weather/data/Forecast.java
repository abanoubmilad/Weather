package werpx.weather.data;


import io.realm.RealmObject;

public class Forecast extends RealmObject {

    private long timeStamp;

    private double dayTemp;
    private double minTemp;
    private double maxTemp;

    public Forecast() {
    }

    public Forecast(long timeStamp, double dayTemp, double minTemp, double maxTemp) {
        this.timeStamp = timeStamp;
        this.dayTemp = dayTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
