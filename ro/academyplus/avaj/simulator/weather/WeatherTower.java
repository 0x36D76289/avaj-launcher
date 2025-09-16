package ro.academyplus.avaj.simulator.weather;

import ro.academyplus.avaj.simulator.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}
