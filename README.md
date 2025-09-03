# Avaj launcher

This is an aircraft simulation program implemented in Java following Object-Oriented design patterns.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Make sure `javac` and `java` commands are available in your terminal

## Project structure

```
src
└───ro
    └───academyplus
        └───avaj
            ├───aircraft
            │       Aircraft.java
            │       AircraftFactory.java
            │       Balloon.java
            │       Helicopter.java
            │       JetPlane.java
            │
            ├───exception
            │       InvalidAircraftException.java
            │       InvalidScenarioException.java
            │
            ├───simulator
            │       Coordinates.java
            │       Flyable.java
            │       Simulator.java
            │       WeatherTower.java
            │       InvalidAircraftException.java
            │       InvalidScenarioException.java
            │
            ├───simulator
            │       Coordinates.java
            │       Flyable.java
            │       Simulator.java
            │       InvalidAircraftException.java
            │       InvalidScenarioException.java
            │
            ├───simulator
            │       Coordinates.java
            │       InvalidAircraftException.java
            │       InvalidScenarioException.java
            │
            ├───simulator
            │       InvalidAircraftException.java
            │       InvalidScenarioException.java
            │       InvalidAircraftException.java
            │       InvalidAircraftException.java
            │       InvalidScenarioException.java
            │
            ├───simulator
            │       Coordinates.java
            │       Flyable.java
            │       Simulator.java
            │       WeatherTower.java
            │
            └───weather
                    WeatherProvider.java
                    WeatherType.java
```

## Compilation

1. Generate the sources list:

   ```bash
   find src -name "*.java" > sources.txt
   ```

2. Compile the project:
   ```bash
   javac @sources.txt
   ```

## Usage

Run the simulator with a scenario file:

```bash
java ro.academyplus.avaj.simulator.Simulator scenario.txt
```

## Scenario file format

The first line contains the number of weather changes to simulate.
Each following line describes an aircraft in the format:

```
TYPE NAME LONGITUDE LATITUDE HEIGHT
```

Example scenario file:

```
25
Balloon B1 2 3 20
JetPlane J1 23 44 32
Helicopter H1 654 33 20
Helicopter H4 1 2 0
```

## Output

The simulation will generate a `simulation.txt` file with the simulation results.

## Design patterns used

- Observer Pattern : Weather tower notifies aircraft of weather changes
- Singleton Pattern : WeatherProvider ensures single instance
- Factory Pattern : AircraftFactory creates aircraft instances

## Features

- Custom exception handling for invalid input
- Validation of scenario files
- Aircraft behavior based on weather conditions
- Automatic landing when aircraft reach height 0
- Unique ID assignment for each aircraft
