package Abstraction_Lab.HotelReservation;

public class PriceCalculator {

    public static String CalculatePrice(double pricePerDay, int numberOfDays, Season season, Discount discount){

        int multiplier = season.getValue();
        double discountMultiplier = discount.getValue() / 100.0;
        double priceBeforeDiscount = numberOfDays * pricePerDay * multiplier;
        double discountedAmount = priceBeforeDiscount * discountMultiplier;
        double price = priceBeforeDiscount - discountedAmount;

        return String.format("%.2f", price);
    }
}
