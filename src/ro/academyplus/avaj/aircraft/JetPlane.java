package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.Flyable;
import ro.academyplus.avaj.simulator.WeatherTower;
import ro.academyplus.avaj.weather.WeatherType;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        WeatherType weather = getCurrentWeather();
        String message = "";

        switch (weather) {
            case SUN:
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                message = "Let's enjoy the good weather and take some pics.";
                break;
            case RAIN:
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                message = "It's raining. Better watch out for lightings.";
                break;
            case FOG:
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                message = "This fog is making navigation tricky!";
                break;
            case SNOW:
                coordinates.setHeight(coordinates.getHeight() - 7);
                message = "OMG! Winter is coming!";
                break;
        }

        WeatherTower.log("JetPlane#" + name + "(" + id + "): " + message);

        if (isLanded()) {
            land(weatherTower);
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        WeatherTower.log("Tower says: JetPlane#" + name + "(" + id + ") registered to weather tower.");
    }
}
