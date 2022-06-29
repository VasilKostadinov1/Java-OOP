package Abstraction_Exercises.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RankPowers rankPowers = RankPowers.valueOf(scanner.nextLine());
        SuitPowers suitPowers = SuitPowers.valueOf(scanner.nextLine());

        Card card = new Card(rankPowers, suitPowers);

        System.out.printf("Card name: %s of %s; Card power: %d%n", rankPowers, suitPowers, card.getPower());
    }
}
