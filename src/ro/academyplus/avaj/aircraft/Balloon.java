package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.Flyable;
import ro.academyplus.avaj.simulator.WeatherTower;
import ro.academyplus.avaj.weather.WeatherType;

public class Balloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        WeatherType weather = getCurrentWeather();
        String message = "";

        switch (weather) {
            case SUN:
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                message = "Let's enjoy the good weather and take some pics.";
                break;
            case RAIN:
                coordinates.setHeight(coordinates.getHeight() - 5);
                message = "Damn you rain! You messed up my balloon.";
                break;
            case FOG:
                coordinates.setHeight(coordinates.getHeight() - 3);
                message = "This fog is making me dizzy!";
                break;
            case SNOW:
                coordinates.setHeight(coordinates.getHeight() - 15);
                message = "It's snowing. We're gonna crash.";
                break;
        }

        WeatherTower.log("Balloon#" + name + "(" + id + "): " + message);

        if (isLanded()) {
            land(weatherTower);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        WeatherTower.log("Tower says: Balloon#" + name + "(" + id + ") registered to weather tower.");
    }
}
