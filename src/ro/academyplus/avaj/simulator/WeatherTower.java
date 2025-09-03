package ro.academyplus.avaj.simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherTower {
    private List<Flyable> observers = new ArrayList<>();
    private static FileWriter logWriter;

    public WeatherTower() {
        try {
            logWriter = new FileWriter("simulation.txt", false);
        } catch (IOException e) {
            System.err.println("Error creating simulation.txt: " + e.getMessage());
        }
    }

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable observer : new ArrayList<>(observers)) {
            observer.updateConditions();
        }
    }

    public void changeWeather() {
        conditionsChanged();
    }

    public static void log(String message) {
        try {
            if (logWriter != null) {
                logWriter.write(message + System.lineSeparator());
                logWriter.flush();
            }
        } catch (IOException e) {
            System.err.println("Error writing to simulation.txt: " + e.getMessage());
        }
    }

    public static void closeLog() {
        try {
            if (logWriter != null) {
                logWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing simulation.txt: " + e.getMessage());
        }
    }
}
