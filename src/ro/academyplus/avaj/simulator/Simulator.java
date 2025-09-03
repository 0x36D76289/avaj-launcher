package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.aircraft.AircraftFactory;
import ro.academyplus.avaj.aircraft.Balloon;
import ro.academyplus.avaj.aircraft.Helicopter;
import ro.academyplus.avaj.aircraft.JetPlane;
import ro.academyplus.avaj.exception.InvalidAircraftException;
import ro.academyplus.avaj.exception.InvalidScenarioException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> aircrafts = new ArrayList<>();
    private static int simulationCount;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ro.academyplus.avaj.simulator.Simulator <scenario_file>");
            return;
        }

        try {
            parseScenarioFile(args[0]);
            runSimulation();
        } catch (InvalidScenarioException | InvalidAircraftException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            WeatherTower.closeLog();
        }
    }

    private static void parseScenarioFile(String filename) throws IOException, InvalidScenarioException, InvalidAircraftException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line == null) {
                throw new InvalidScenarioException("Scenario file is empty");
            }

            // Parse simulation count
            try {
                simulationCount = Integer.parseInt(line.trim());
                if (simulationCount <= 0) {
                    throw new InvalidScenarioException("Simulation count must be a positive integer");
                }
            } catch (NumberFormatException e) {
                throw new InvalidScenarioException("Invalid simulation count: " + line);
            }

            // Initialize weather tower
            weatherTower = new WeatherTower();

            // Parse aircraft lines
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                parseAircraftLine(line);
            }

            if (aircrafts.isEmpty()) {
                throw new InvalidScenarioException("No valid aircraft found in scenario file");
            }
        }
    }

    private static void parseAircraftLine(String line) throws InvalidAircraftException {
        String[] parts = line.split("\\s+");
        if (parts.length != 5) {
            throw new InvalidAircraftException("Invalid aircraft line format: " + line);
        }

        try {
            String type = parts[0];
            String name = parts[1];
            int longitude = Integer.parseInt(parts[2]);
            int latitude = Integer.parseInt(parts[3]);
            int height = Integer.parseInt(parts[4]);

            if (longitude < 0 || latitude < 0 || height < 0 || height > 100) {
                throw new InvalidAircraftException("Invalid coordinates: longitude=" + longitude +
                    ", latitude=" + latitude + ", height=" + height);
            }

            Flyable aircraft = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
            aircrafts.add(aircraft);

            // Register aircraft with weather tower
            if (aircraft instanceof JetPlane) {
                ((JetPlane) aircraft).registerTower(weatherTower);
            } else if (aircraft instanceof Helicopter) {
                ((Helicopter) aircraft).registerTower(weatherTower);
            } else if (aircraft instanceof Balloon) {
                ((Balloon) aircraft).registerTower(weatherTower);
            }

        } catch (NumberFormatException e) {
            throw new InvalidAircraftException("Invalid numeric values in aircraft line: " + line);
        } catch (IllegalArgumentException e) {
            throw new InvalidAircraftException(e.getMessage());
        }
    }

    private static void runSimulation() {
        for (int i = 0; i < simulationCount; i++) {
            weatherTower.changeWeather();
        }
    }
}
