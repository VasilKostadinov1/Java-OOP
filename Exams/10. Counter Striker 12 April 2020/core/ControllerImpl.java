package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;
import CounterStriker.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {


    private Repository<Gun> guns;
    private Repository<Player> players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        guns.add(gun);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);  // Express, Buffalo, Assault
        //return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, type); !!! wants the name of the gun
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        //If the gun is not found throw NullPointerException
        //Gun gun = new GunRepository().findByName(gunName);   // !!! 34/150 reducing points
        Gun gun = this.guns.findByName(gunName);
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }
        //If the player type is invalid, throw
        Player player;
        switch (type) {
            case "Terrorist":
                player = new Terrorist(username, health, armor, gun);
                break;
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gun);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        this.players.add(player);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        //Game starts with all players that are alive!
        List<Player> playersAlive = this.players
                .getModels()
                .stream()
                .filter(Player::isAlive)
                .collect(Collectors.toList());
        //Returns the result from the start() method. // it is in Interface Field
        return this.field.start(playersAlive);
    }

    @Override
    public String report() {
        //. Order them by type alphabetically, then by health descending, then by username alphabetically.
        StringBuilder sb = new StringBuilder();
        this
                .players
                .getModels()
                .stream()
                .sorted((p1, p2) -> {
                    int result = p1.getClass().getSimpleName().compareTo(p2.getClass().getSimpleName()); // by type alphabetically
                    if (result == 0) {
                        result = Integer.compare(p2.getHealth(), p1.getHealth());  //then by health descending
                    }
                    if (result == 0) {
                        p1.getUsername().compareTo(p2.getUsername());  //then by username alphabetically.
                    }
                    return result;
                })
                .forEach(p ->
                        sb.append(p.toString())
                                .append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
