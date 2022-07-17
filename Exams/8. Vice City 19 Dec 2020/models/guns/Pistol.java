package viceCity.models.guns;

public class Pistol extends BaseGun {


    private static final int BULLETS_PER_BARREL = 10;
    private static final int BULLETS_TOTAL = 100;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, BULLETS_TOTAL);
    }

    @Override
    public int fire() {
        //The pistol shoots only one bullet.
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            reload();
        }if (getBulletsPerBarrel() > 0) {
            this.setBulletsPerBarrel(getBulletsPerBarrel() - 1);
        }
        return 1;
    }
    private void reload() {
        setTotalBullets(getTotalBullets()-BULLETS_PER_BARREL);
        setBulletsPerBarrel(BULLETS_PER_BARREL);
    }
}
