package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {

    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_LAPS);
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        //Driver driver1 = drivers.stream().filter(d->d.getName().equals(name)).findFirst().orElse(null);
        if (driver == null) {
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
        }
        if (!driver.getCanParticipate()){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,driver.getName()));
        }
        //TODO .contains
        //boolean driverExists = driver.getName()!=null;
        //if (driverExists){
        if(drivers.contains(driver)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_ALREADY_ADDED, driver.getName(),name));
        }
        this.drivers.add(driver);

    }
}
