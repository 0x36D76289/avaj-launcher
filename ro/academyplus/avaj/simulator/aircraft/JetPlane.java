package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;

public class JetPlane extends Aircraft {
    public JetPlane(String name, Coordinates coordinates) {
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
                lat += 10;
                hgt += 2;
                message = "This is hot.";
                break;
            case "RAIN":
                lat += 5;
                message = "It's raining. Better watch out for lightings.";
                break;
            case "FOG":
                lat += 1;
                message = "OMG! Can't see a thing!";
                break;
            case "SNOW":
                hgt -= 7;
                message = "OMG! Winter is coming!";
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
