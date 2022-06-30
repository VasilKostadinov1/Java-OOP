package Encapsulation_Exercises.ShoppingSpree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static boolean invalidOperation = false;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String[] peopleInfo = readData(scanner);

        Map<String, Person> peopleMap = new LinkedHashMap<>();
        addPeople(peopleInfo, peopleMap);

        if (invalidOperation) {
            return;
        }

        String[] productsInfo = readData(scanner);

        Map<String, Product> productsMap = new LinkedHashMap<>();
        addProducts(productsInfo, productsMap);

        if (invalidOperation) {
            return;
        }

        goShopping(scanner, peopleMap, productsMap);

        printPeopleAndProducts(peopleMap);
    }

    private static void goShopping(Scanner scanner, Map<String, Person> peopleMap, Map<String, Product> productsMap) {
        String commands = scanner.nextLine();

        while (!commands.equals("END")) {
            String[] data = commands.split("[\\s]+");

            String personName = data[0];
            String productName = data[1];

            try {
                Person person = peopleMap.get(personName);
                Product product = productsMap.get(productName);

                person.buyProduct(product);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            commands = scanner.nextLine();
        }
    }

    private static void printPeopleAndProducts(Map<String, Person> peopleMap) {
        peopleMap.values()
                .forEach(person -> {
                    System.out.print(person.getName() + " - ");

                    if (person.getProducts().isEmpty()) {
                        System.out.println("Nothing bought");

                    } else {
                        System.out.println(person
                                .getProducts()
                                .stream()
                                .map(Product::getName)
                                .collect(Collectors.joining(", ")));
                    }
                });
    }

    private static void addPeople(String[] peopleInfo, Map<String, Person> peopleMap) {
        int index = 0;

        for (int i = 0; i < peopleInfo.length; i += 2) {
            String name = peopleInfo[index++];
            double money = Double.parseDouble(peopleInfo[index++]);

            try {
                Person person = new Person(name, money);
                peopleMap.putIfAbsent(name, person);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());

                invalidOperation = true;
                return;
            }
        }
    }

    private static void addProducts(String[] productsInfo, Map<String, Product> productsMap) {
        int index = 0;

        for (int i = 0; i < productsInfo.length; i += 2) {
            String name = productsInfo[index++];
            double cost = Double.parseDouble(productsInfo[index++]);

            try {
                Product product = new Product(name, cost);
                productsMap.putIfAbsent(name, product);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());

                invalidOperation = true;
                return;
            }
        }
    }

    private static String[] readData(Scanner scanner) {
        return scanner.nextLine().split("[\\s=;]+");
    }
}
