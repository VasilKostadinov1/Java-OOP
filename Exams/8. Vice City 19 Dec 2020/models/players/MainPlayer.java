package viceCity.models.players;

public class MainPlayer extends BasePlayer{

    private static final int INITIAL_POINTS = 100;
    private static final String NAME = "Tommy Vercetti";


    public MainPlayer() {
        super(NAME, INITIAL_POINTS);
    }
}
