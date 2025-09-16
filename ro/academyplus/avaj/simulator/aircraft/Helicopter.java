package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;

public class Helicopter extends Aircraft {
    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);
        int lon = this.coordinates.getLongitude();
        int lat = this.coordinates.getLatitude();
        int hgt = this.coordinates.getHeight();
        String message = "";

        switch (weather) {
            case "SUN":
                lon += 10;
                hgt += 2;
                message = "This is hot.";
                break;
            case "RAIN":
                lon += 5;
                message = "Rain... My blades are getting rusty.";
                break;
            case "FOG":
                lon += 1;
                message = "I can't see the landing pad!";
                break;
            case "SNOW":
                hgt -= 12;
                message = "My rotor is going to freeze!";
                break;
        }

        if (hgt > 100)
            hgt = 100;

        System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): " + message);

        if (hgt <= 0) {
            System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            System.out.println("Tower says: " + this.getClass().getSimpleName() + "#" + this.name + "(" + this.id
                    + ") unregistered from weather tower.");
        } else {
            this.coordinates = new Coordinates(lon, lat, hgt);
        }
    }
}
