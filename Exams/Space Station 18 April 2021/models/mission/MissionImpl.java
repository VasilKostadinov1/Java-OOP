package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission{


    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        // TODO
        Collection<String> items = planet.getItems();


        for (Astronaut astronaut : astronauts) {

            while (planet.getItems().iterator().hasNext() && astronaut.canBreath()) {
                astronaut.getBag().getItems().add(items.iterator().next());
                astronaut.breath();
                items.remove(items.iterator().next());

            }
        }
    }
}
