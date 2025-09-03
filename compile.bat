@echo off
echo Compiling Avaj Launcher...

if exist sources.txt del sources.txt
for /r src %%f in (*.java) do echo %%f >> sources.txt

javac -d . @sources.txt

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo Usage: java ro.academyplus.avaj.simulator.Simulator scenario.txt
) else (
    echo Compilation failed!
)

pause
