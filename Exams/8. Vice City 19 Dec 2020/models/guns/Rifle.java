package viceCity.models.guns;

public class Rifle extends BaseGun{


    private static final int BULLETS_PER_BARREL = 50;
    private static final int BULLETS_TOTAL = 500;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, BULLETS_TOTAL);
    }

    @Override
    //The rifle can shoot with 5 bullets.
    public int fire() {
        if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            reload();
        }
        if (getBulletsPerBarrel() > 0) {
            this.setBulletsPerBarrel(getBulletsPerBarrel() - 5);
        }
        return 5;
    }

    private void reload() {
        setTotalBullets(getTotalBullets()-BULLETS_PER_BARREL);
        setBulletsPerBarrel(BULLETS_PER_BARREL);
    }
}
