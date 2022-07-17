package christmasRaces.repositories;

import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RaceRepository implements Repository<Race> {

    private List<Race> racers;

    public RaceRepository() {
        this.racers = new ArrayList<>();
    }
    @Override
    public Race getByName(String name) {
        return this.racers.stream().filter(r -> r.getName().equals(name)).findFirst().orElse(null);
    }
    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(racers);
    }
    @Override
    public void add(Race model) {
        this.racers.add(model);
    }
    @Override
    public boolean remove(Race model) {
        return this.racers.remove(model);
    }
}
