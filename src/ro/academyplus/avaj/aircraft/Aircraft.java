package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.Flyable;
import ro.academyplus.avaj.simulator.WeatherTower;
import ro.academyplus.avaj.weather.WeatherProvider;
import ro.academyplus.avaj.weather.WeatherType;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = idCounter++;
        this.name = name;
        this.coordinates = coordinates;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    protected abstract void updateConditions();

    protected boolean isLanded() {
        return coordinates.getHeight() <= 0;
    }

    protected void land(WeatherTower weatherTower) {
        WeatherTower.log(this.getClass().getSimpleName() + "#" + name + "(" + id + ") landing.");
        weatherTower.unregister((Flyable) this);
        WeatherTower.log("Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ") unregistered from weather tower.");
    }

    protected WeatherType getCurrentWeather() {
        return WeatherProvider.getInstance().getCurrentWeather(coordinates);
    }
}
