package ro.academyplus.avaj.simulator.weather;

import ro.academyplus.avaj.simulator.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = null;
    private String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int index = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight()) % 4;
        return weather[index];
    }
}
