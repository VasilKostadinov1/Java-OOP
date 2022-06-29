package Abstraction_Exercises.CardSuit;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Card Suits:");

        Arrays.stream(CardSuits.values())
                .forEach(value-> System.out.printf("Ordinal value: %d; Name value: %s%n",value.ordinal(),value.name()));

        // ordinal - Returns the ordinal of this enumeration constant (its position in its enum declaration,
        // where the initial constant is assigned an ordinal of zero).
    }
}
