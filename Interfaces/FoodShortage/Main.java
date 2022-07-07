package FoodShortage_04;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        //List<Person> data = new ArrayList<>();
        Map<String, Buyer> buyers = new HashMap<>();

        for (int i = 0; i < n; i++) {

            String[] input = scanner.nextLine().split(" ");
            //name} {age} {id} {birthdate}
            String name = input[0];
            int age = Integer.parseInt(input[1]);
            if (input.length == 4) {
                String id = input[2];
                String birthdate = input[3];

                Citizen citizen = new Citizen(name, age, id, birthdate);
                buyers.put(name,citizen);

            } else {// "{name} {age}{group}
                String group = input[2];
                Rebel rebel = new Rebel(name, age, group);
                buyers.put(name,rebel);
            }
        }
        String newInput = scanner.nextLine();
        while (!newInput.equals("End")){
            if (buyers.containsKey(newInput)) {
                Buyer buyer = buyers.get(newInput);
                buyer.buyFood();
            }
            newInput = scanner.nextLine();
        }
        int sumTotal = buyers.values().stream().mapToInt(b -> b.getFood()).sum();
        System.out.println(sumTotal);

    }
}
