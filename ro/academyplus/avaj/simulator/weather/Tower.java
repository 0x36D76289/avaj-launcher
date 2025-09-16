package ro.academyplus.avaj.simulator.weather;

import ro.academyplus.avaj.simulator.aircraft.Flyable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable flyable : new ArrayList<>(observers)) {
            flyable.updateConditions();
        }
    }
}
