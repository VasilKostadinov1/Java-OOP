package Inheritance_Lab.StackOfStrings_05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StackOfStrings sos = new StackOfStrings();

        sos.push("one");
        sos.push("two");
        sos.push("three");

        System.out.println(sos.isEmpty());
        System.out.println(sos.peek());

        System.out.println(sos.peek());
        System.out.println(sos.peek());
        System.out.println(sos.peek());
    }
}
