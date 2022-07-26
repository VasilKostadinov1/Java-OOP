package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.GunImpl;

public abstract class PlayerImpl implements Player {

    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    public void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    public void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    //decreases the Player's armor and health. First you need to reduce the armor.
    // If the armor reaches 0, transfer the damage to health points.
    // If the health points are less than or equal to zero, the player is dead.
    public void takeDamage(int points) {

        int decreaseHealth = points > this.getArmor() ? points - this.getArmor() : 0;
        this.setArmor(Math.max(this.getArmor() - points, 0));
        this.setHealth(Math.max(this.getHealth() - decreaseHealth, 0));

//        int damage = points;
//        if (damage >= this.getArmor()) {
//            damage -= this.getArmor();
//            this.armor = 0;
//            this.health -= damage;
//        } else {
//            this.armor -= damage;
//        }
//        if (this.health < 0) {
//            this.health = 0;
//        }

    }

//    "{player type}: {player username}
//    --Health: {player health}
//    --Armor: {player armor}
//    --Gun: {player gun name}"


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s: %s", getClass().getSimpleName(), username)).append(System.lineSeparator());
        sb.append(String.format("--Health: %d", health)).append(System.lineSeparator());
        sb.append(String.format("--Armor: %d", armor)).append(System.lineSeparator());
        sb.append(String.format("--Gun: %s", gun.getName())).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
