package CounterStriker.models.guns;

public class Rifle extends GunImpl {

    private static final int BULLETS_PER_FIRE = 10;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        //return Math.max(0, this.getBulletsCount() - BULLETS_PER_FIRE);
        if (this.getBulletsCount() >= BULLETS_PER_FIRE) {
            this.setBulletsCount(this.getBulletsCount() - BULLETS_PER_FIRE);
            return BULLETS_PER_FIRE;
        }
        return 0;
    }
}
