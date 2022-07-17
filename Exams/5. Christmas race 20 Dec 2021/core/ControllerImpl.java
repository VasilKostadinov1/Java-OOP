package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    private static final int MINIMUM_DRIVERS_PER_RACE = 3;

    //values upon initialization in the specified order. The constructor should have a public access modifier.
    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override //If a driver with the given name already exists in the driver repository, throw
    public String createDriver(String driverName) {
        Driver existingDriver = driverRepository.getByName(driverName);
        if (existingDriver != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }
        //Creates a Driver with the given name and adds it to the appropriate repository
        Driver driver = new DriverImpl(driverName);
        driverRepository.add(driver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        //If the Car already exists in the appropriate repository throw ...
        Car existingCar = carRepository.getByName(model);
        if (existingCar != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }
        Car car = null; //Create a Car with the provided model and horsepower and add it to the repository
        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
        } else if (type.equals("Sports")) {
            car = new SportsCar(model, horsePower);
        }
        this.carRepository.add(car);
        return String.format(OutputMessages.CAR_CREATED, type + "Car", model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver existingDriver = this.driverRepository.getByName(driverName);
        if (existingDriver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        Car existingCar = this.carRepository.getByName(carModel);
        if (existingCar == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }
        //you should add the Car to the Driver
        existingDriver.addCar(existingCar);
        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        Driver driver = this.driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);  // add the Driver to the Race
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        //If the Race does not exist in RaceRepository, throw...
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        //If the participants in the race are less than 3, throw...
        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, MINIMUM_DRIVERS_PER_RACE));
        }
        //you should arrange all Drivers and then return the three fastest Drivers. -
        //descending order by the result of CalculateRacePoints
        Collection<Driver> drivers = race.getDrivers();
        int laps = race.getLaps();

        List<Driver> fastestThree = drivers.stream()
                .sorted((d2, d1) ->
                        Double.compare(d1.getCar().calculateRacePoints(laps),
                                d2.getCar().calculateRacePoints(laps)))
                .limit(3)
                .collect(Collectors.toList());

        //In the end, if everything is valid remove this Race from the race repository.
        raceRepository.remove(race);

        Driver first = fastestThree.get(0);
        Driver second = fastestThree.get(1);
        Driver third = fastestThree.get(2);

//        •	"Driver {first driver name} wins {race name} race."
//        "Driver {second driver name} is second in {race name} race."
//        "Driver {third driver name} is third in {race name} race."

        StringBuilder sb = new StringBuilder();
        String nameRace = race.getName();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, first.getName(), nameRace))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, second.getName(), nameRace))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, third.getName(), nameRace))
                .append(System.lineSeparator());
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race existingRace = raceRepository.getByName(name);
        if (existingRace != null) {  // already exists, throw ...
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED, name);
    }


}
