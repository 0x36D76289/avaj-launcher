package ro.academyplus.avaj.weather;

import ro.academyplus.avaj.simulator.Coordinates;

public class WeatherProvider {
    private static WeatherProvider instance = null;
    private WeatherType[] weather = {WeatherType.RAIN, WeatherType.FOG, WeatherType.SUN, WeatherType.SNOW};

    private WeatherProvider() {}

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public WeatherType getCurrentWeather(Coordinates coordinates) {
        // Weather generation algorithm based on coordinates
        int weatherIndex = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight()) % 4;
        return weather[weatherIndex];
    }
}
