package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.aircraft.AircraftFactory;
import ro.academyplus.avaj.simulator.aircraft.Flyable;
import ro.academyplus.avaj.simulator.weather.WeatherTower;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: Please provide one argument: the scenario file");
            return;
        }

        String scenarioFile = args[0];
        WeatherTower weatherTower = new WeatherTower();
        List<Flyable> aircrafts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(scenarioFile))) {
            String line = reader.readLine();
            if (line == null) {
                System.err.println("Error: Scenario file is empty");
                return;
            }

            int simulationRuns = Integer.parseInt(line.trim());
            if (simulationRuns < 0) {
                System.err.println("Error: Number of simulations must be non-negative");
                return;
            }

            System.setOut(new PrintStream(new FileOutputStream("simulation.txt")));

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length != 5) {
                    System.err.println("Error: Invalid line in scenario file: " + line);
                    continue;
                }
                Flyable aircraft = AircraftFactory.newAircraft(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]));

                if (aircraft != null) {
                    aircrafts.add(aircraft);
                }
            }

            for (Flyable aircraft : aircrafts) {
                aircraft.registerTower(weatherTower);
            }

            for (int i = 0; i < simulationRuns; i++) {
                weatherTower.changeWeather();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Scenario file not found: " + scenarioFile);
        } catch (IOException e) {
            System.err.println("Error reading the scenario file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format in scenario file.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
