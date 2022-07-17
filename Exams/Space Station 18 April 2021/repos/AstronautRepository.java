package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AstronautRepository implements Repository<Astronaut> {

    private Collection<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(astronauts);
    }
    @Override
    public void add(Astronaut model) {
        this.astronauts.add(model);
        //•	Every astronaut is unique and it is guaranteed that there will not be an astronaut with the same name
//        if (this.astronauts.stream().noneMatch(a -> a.getName().equals(model.getName()))) {
//        }
    }

    @Override
    public boolean remove(Astronaut model) {
        return this.astronauts.remove(model);
    }

    @Override
    public Astronaut findByName(String name) {
        return this.astronauts.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
    }
}
