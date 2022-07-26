package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.Collection;
import java.util.stream.Collectors;

public class FieldImpl implements Field {


    @Override
    public String start(Collection<Player> players) {
        //We have only 1 collection, and we have to split it in 2!!!!
        //Separates the players in two types - Terrorist and Counter Terrorist.
        //The game continues until one of the teams is completely dead (all players have 0 health).
        // use "instanceof" or "getClass.getSimpleName"
        Collection<Player> contraTerrorists = players
                .stream()
                .filter(p -> p instanceof CounterTerrorist)
                .collect(Collectors.toList());
        Collection<Player> terrorists = players
                .stream()
                .filter(p -> p instanceof Terrorist) //p -> p.getClass().getSimpleName().equals("Terrorist")
                .collect(Collectors.toList());

        while (contraTerrorists.stream().anyMatch(Player::isAlive) && terrorists.stream().anyMatch(Player::isAlive)) {
            //The terrorists attack first...
            for (Player terrorist : terrorists) {
                for (Player contraTerrorist : contraTerrorists) {
                    contraTerrorist.takeDamage(terrorist.getGun().fire());  //void takeDamage(int points);
                }
            }
            contraTerrorists = contraTerrorists.stream().filter(Player::isAlive).collect(Collectors.toList());

            //....and after that the counter terrorists
            for (Player contraTerrorist : contraTerrorists) {
                for (Player terrorist : terrorists) {
                    terrorist.takeDamage(contraTerrorist.getGun().fire());
                }
            }
            terrorists = terrorists.stream().filter(Player::isAlive).collect(Collectors.toList());
        }
        return terrorists.stream()
                .anyMatch(Player::isAlive)
                ? OutputMessages.TERRORIST_WINS
                : OutputMessages.COUNTER_TERRORIST_WINS;
    }
}

