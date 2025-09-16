package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.weather.WeatherTower;

public interface Flyable {
    void updateConditions();

    void registerTower(WeatherTower weatherTower);
}
