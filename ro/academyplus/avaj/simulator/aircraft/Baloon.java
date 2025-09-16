package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.Coordinates;

public class Baloon extends Aircraft {
    public Baloon(String name, Coordinates coordinates) {
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
                lon += 2;
                hgt += 4;
                message = "Let's enjoy the good weather and take some pics.";
                break;
            case "RAIN":
                hgt -= 5;
                message = "Damn you rain! You messed up my balloon.";
                break;
            case "FOG":
                hgt -= 3;
                message = "Where am I? This fog is thick.";
                break;
            case "SNOW":
                hgt -= 15;
                message = "It's snowing. We're gonna crash.";
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
