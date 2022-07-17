package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private int countExploredPlanets; // needed for the final Print

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        //Planet planet = this.planetRepository.findByName(planetName);   // 75/150
        //this.planetRepository.add(planet);
        Planet planet = new PlanetImpl(planetName);                    // 87/150
        for (String item : items) {
            planet.getItems().add(item);
        }
        this.planetRepository.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        this.astronautRepository.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> suitableAstronauts = astronautRepository
                .getModels()
                .stream().filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());
        if (suitableAstronauts.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        int countBeforeMission = suitableAstronauts.size();
        Mission mission = new MissionImpl();
        Planet planet = this.planetRepository.findByName(planetName);
        mission.explore(planet, suitableAstronauts);
        countExploredPlanets++;
        int dead = suitableAstronauts.size();
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, countBeforeMission - dead);
    }

    @Override
//    "{exploredPlanetsCount} planets were explored!"
//    "Astronauts info:"

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d planets were explored!", countExploredPlanets)).append(System.lineSeparator());
        sb.append("Astronauts info:").append(System.lineSeparator());

        this.astronautRepository
                .getModels()
                .forEach(a -> sb.append(a.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
