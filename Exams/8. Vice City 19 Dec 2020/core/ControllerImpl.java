package viceCity.core;

import viceCity.common.ExceptionMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.common.ConstantMessages;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Player mainPlayer;
    private ArrayDeque<Gun> guns;
    private Neighbourhood neighbourhood;
    private Map<String, Player> players;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
        this.players = new LinkedHashMap<>();   // !!! Initiliaze
    }

    @Override
    public String addPlayer(String name) {
        //Creates a civil player with the given name
        Player player = new CivilPlayer(name);
        this.players.putIfAbsent(player.getName(), player);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {

        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }
        this.guns.add(gun);
        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = this.guns.poll();
        //•	If there no guns in the queue, return
        if (gun == null) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        //•	If the name of the player is "Vercetti", you need to add the gun to the main player's repository and return
        if (name.equals("Vercetti")) {
            this.mainPlayer.getGunRepository().add(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
        }
        //•	If you receive a name which doesn't exist, you should return the following message
        Player player = this.players.get(name);
        if (player == null) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }
        //•	If everything is successful, you should add the gun to the player's repository and return
        player.getGunRepository().add(gun);
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
    }

    @Override
    public String fight() {

        this.neighbourhood.action(mainPlayer, players.values());

        //•    If the main player still has all of his points and no one is dead or harmed from the civil players,
        if (this.mainPlayer.getLifePoints() == 100 && this.players
                .values()
                .stream()
                .allMatch(player -> player.getLifePoints() == 50))

            return ConstantMessages.FIGHT_HOT_HAPPENED;

        List<Player> deadPlayers = this.players
                .values()
                .stream()
                .filter(player -> !player.isAlive())
                .collect(Collectors.toList());

//        If any of the players has different life points, you should return the following message:
//        "A fight happened:"
//        "Tommy live points: {main player life points}!"
//        "Tommy has killed: {dead civil players} players!"
//        "Left Civil Players: {civil players count}!"

        StringBuilder sb = new StringBuilder();
        sb.append("A fight happened:").append(System.lineSeparator());
        sb.append(String.format("Tommy live points: %d!", mainPlayer.getLifePoints())).append(System.lineSeparator());
        sb.append(String.format("Tommy has killed: %d players!", deadPlayers.size())).append(System.lineSeparator());
        sb.append(String.format("Left Civil Players: %d!", players.size() - deadPlayers.size()));

//        for (Player deadPlayer : deadPlayers) {
//            players.remove(deadPlayer.getName());
//        }
        return sb.toString().trim();

    }

}