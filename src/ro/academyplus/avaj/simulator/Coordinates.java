package ro.academyplus.avaj.simulator;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = Math.max(0, Math.min(100, height)); // Ensure height is in 0-100 range
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int longitude) {
        this.longitude = Math.max(0, longitude); // Ensure positive coordinates
    }

    public void setLatitude(int latitude) {
        this.latitude = Math.max(0, latitude); // Ensure positive coordinates
    }

    public void setHeight(int height) {
        this.height = Math.max(0, Math.min(100, height)); // Ensure height is in 0-100 range
    }
}
