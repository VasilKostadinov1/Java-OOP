package Abstraction_Exercises.CardsWithPower;

public class Card {

    private RankPowers rankPowers;
    private SuitPowers suitPowers;

    public Card(RankPowers rankPowers, SuitPowers suitPowers) {
        this.rankPowers = rankPowers;
        this.suitPowers = suitPowers;
    }

    public int getPower(){
        return rankPowers.getPower()+ suitPowers.getSuitPower();
    }
}
