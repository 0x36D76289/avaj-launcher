package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.weather.WeatherTower;

public abstract class Aircraft implements Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;
    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = this.nextId();
    }

    private long nextId() {
        return ++idCounter;
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id
                + ") registered to weather tower.");
    }

    @Override
    public abstract void updateConditions();
}
