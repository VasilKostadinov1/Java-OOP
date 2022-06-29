package Abstraction_Exercises.CardRank;

import Abstraction_Exercises.CardSuit.CardSuits;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Card Ranks:");

        Arrays.stream(CardRank.values())
                .forEach(value ->
                        System.out.printf("Ordinal value: %d; Name value: %s%n",
                                value.ordinal(), value.name()));

        /*for (CardRank value : CardRank.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", value.ordinal(), value.name());
        }*/


    }
}
