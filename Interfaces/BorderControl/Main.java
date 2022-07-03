package BorderControl_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> data = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            Identifiable identifiable;

            String name = tokens[0];
            // MK-13 558833251
            if (tokens.length == 2) {
                String id = tokens[1];
                identifiable = new Robot(name, id);
            } else {
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                identifiable = new Citizen(name, age, id);
            }
            data.add(identifiable);
            input = scanner.nextLine();
        }
        String lastFakeDigits = scanner.nextLine();

        System.out.println(data.stream()
                .map(Identifiable::getId)
                .filter(id -> id.endsWith(lastFakeDigits))
                .collect(Collectors.joining(System.lineSeparator())));




    }
}
