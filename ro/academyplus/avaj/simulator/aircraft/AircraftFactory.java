package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if ("Helicopter".equalsIgnoreCase(type)) {
            return new Helicopter(name, coordinates);
        } else if ("JetPlane".equalsIgnoreCase(type)) {
            return new JetPlane(name, coordinates);
        } else if ("Baloon".equalsIgnoreCase(type)) {
            return new Baloon(name, coordinates);
        } else {
            return null;
        }
    }
}
