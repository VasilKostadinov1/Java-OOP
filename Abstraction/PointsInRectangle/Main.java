package Abstraction_Lab.PointsInRectangle;

import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = readData(scanner);

        int bottomLeftPointA = coordinates[0];
        int topRightPointA = coordinates[1];

        int bottomLeftPointB = coordinates[2];
        int topRightPointB = coordinates[3];

        Point pointA = new Point(bottomLeftPointA, topRightPointA);
        Point pointB = new Point(bottomLeftPointB, topRightPointB);

        Rectangle rectangle = new Rectangle(pointA, pointB);

        int count = Integer.parseInt(scanner.nextLine());

        while (count-- > 0) {
            int[] coordinatesPoints = readData(scanner);

            int bottomLeftPoint = coordinatesPoints[0];
            int topRightPoint = coordinatesPoints[1];

            Point currentPoint = new Point(bottomLeftPoint, topRightPoint);

            boolean inside = rectangle.isInside(currentPoint);

            System.out.println(inside);
        }


    }

    private static int[] readData(Scanner scanner) {
         return Arrays.stream(scanner.nextLine()
                        .split("[\\s]+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
