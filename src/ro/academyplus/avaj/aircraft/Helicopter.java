package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.Flyable;
import ro.academyplus.avaj.simulator.WeatherTower;
import ro.academyplus.avaj.weather.WeatherType;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        WeatherType weather = getCurrentWeather();
        String message = "";

        switch (weather) {
            case SUN:
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                message = "This is hot.";
                break;
            case RAIN:
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                message = "Rain drops keep falling on my rotor!";
                break;
            case FOG:
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                message = "Can't see a thing in this fog!";
                break;
            case SNOW:
                coordinates.setHeight(coordinates.getHeight() - 12);
                message = "My rotor is going to freeze!";
                break;
        }

        WeatherTower.log("Helicopter#" + name + "(" + id + "): " + message);

        if (isLanded()) {
            land(weatherTower);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        WeatherTower.log("Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower.");
    }
}
