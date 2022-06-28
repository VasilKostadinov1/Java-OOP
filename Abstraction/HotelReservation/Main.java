package Abstraction_Lab.HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("[\\s]+");

        double pricePerDay = Double.parseDouble(tokens[0]);
        int numberOfDays = Integer.parseInt(tokens[1]);
        Season season = Season.valueOf(tokens[2]);
        Discount discount = Discount.valueOf(tokens[3]);

        System.out.println(PriceCalculator.CalculatePrice(pricePerDay, numberOfDays, season, discount));
    }
}
