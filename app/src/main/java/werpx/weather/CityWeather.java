package werpx.weather;


public class CityWeather {
    private int id;
    private String name;
    private String temp;

    public CityWeather(int id, String name, String temp) {
        this.id = id;
        this.name = name;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
