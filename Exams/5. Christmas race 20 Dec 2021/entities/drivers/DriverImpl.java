package christmasRaces.entities.drivers;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;

public class DriverImpl implements Driver {

    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate = false;

    public DriverImpl(String name) {
        this.setName(name);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    //adds a Car to the Driver
    public void addCar(Car car) {
        //If the given Car is not null, set the current Car as the given one and after that Driver can participate in a race.
        if (car == null) {
            throw new IllegalArgumentException(ExceptionMessages.CAR_INVALID);
        }
        this.car = car;
        canParticipate = true;
    }

    @Override
    public void winRace() {
        this.numberOfWins++;

    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }
}
