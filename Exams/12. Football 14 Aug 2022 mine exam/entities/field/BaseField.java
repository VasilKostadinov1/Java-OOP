package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseField implements Field {

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumEnergy() {
        return this.supplements.stream().mapToInt(Supplement::getEnergy).sum();
    }

    @Override
    public void addPlayer(Player player) {
        if (this.players.size() >= this.capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.players.add(player);

    }

    @Override
    public void removePlayer(Player player) {
        this.players.remove(player);

    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        this.players.forEach(Player::stimulation);
    }

//    "{fieldName} ({fieldType}):
//    Player: {playerName1} {playerName2} {playerName3} (…) / Player: none
//    Supplement: {supplementsCount}
//    Energy: {sumEnergy}"

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());

        String names = this.players.stream().map(Player::getName).collect(Collectors.joining(" "));
        if (this.players.size() == 0) {
            sb.append("Player: none");
        } else {
            sb.append(String.format("Player: %s", names));
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Supplement: %d",this.supplements.size())).append(System.lineSeparator());
        sb.append(String.format("Energy: %d", this.sumEnergy())).append(System.lineSeparator());


        return sb.toString().trim();
    }

    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplement() {
        return this.supplements;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
