package werpx.weather;


public class Forecast {

    private long timeStamp;

    private double dayTemp;
    private double minTemp;
    private double maxTemp;


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

}
