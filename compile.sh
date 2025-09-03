#!/bin/bash
echo "Compiling Avaj Launcher..."

find src -name "*.java" > sources.txt

javac -d . @sources.txt

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Usage: java ro.academyplus.avaj.simulator.Simulator scenario.txt"
else
    echo "Compilation failed!"
fi
