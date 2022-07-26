package CounterStriker.models.guns;

import CounterStriker.common.ExceptionMessages;

public abstract class GunImpl implements Gun {

    private static final int BULLETS_PER_FIRE = 1;

    private String name;
    private int bulletsCount;


    public GunImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_NAME);
        }
        this.name = name;
    }

    public void setBulletsCount(int bulletsCount) {
        if (bulletsCount < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    @Override
    public int fire() {
        //return Math.max(0, bulletsCount - BULLETS_PER_FIRE);
        if (this.bulletsCount >= BULLETS_PER_FIRE) {
            this.bulletsCount -= BULLETS_PER_FIRE;
            return BULLETS_PER_FIRE;
        }
        return 0;
    }
}
