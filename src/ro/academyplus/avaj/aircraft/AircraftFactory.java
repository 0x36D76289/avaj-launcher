package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;
import ro.academyplus.avaj.simulator.Flyable;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type.toLowerCase()) {
            case "jetplane":
                return new JetPlane(name, coordinates);
            case "helicopter":
                return new Helicopter(name, coordinates);
            case "balloon":
                return new Balloon(name, coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + type);
        }
    }
}
