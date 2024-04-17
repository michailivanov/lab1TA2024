package michail;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

//        do {
//            System.out.println("----------------");
//            System.out.println("Menu:");
//            System.out.println("1. Check URL");
//            System.out.println("2. Generate URL");
//            System.out.println("3. Exit");
//            System.out.print("Enter your choice: ");
//
//            // Проверяем, является ли следующий ввод целым числом
//            if (scanner.hasNextInt()) {
//                choice = scanner.nextInt();
//                scanner.nextLine(); // Переходим на следующую строку
//            } else {
//                System.out.println("Invalid choice. Please enter a valid choice.");
//                scanner.nextLine(); // Переходим на следующую строку
//                continue; // Пропускаем оставшуюся часть итерации цикла
//            }
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter URL to check: ");
//                    String inputURL = scanner.nextLine();
//                    boolean isValid = UrlAutomata.automata.stringChecking(inputURL);
//                    if (isValid) {
//                        System.out.println("URL is valid.");
//                    } else {
//                        System.out.println("URL is not valid.");
//                    }
//                    break;
//                case 2:
//                    String url =  UrlAutomata.automata.stringGeneration();
//                    System.out.println("Generated URL: " + url);
//                    System.out.println("TEST: " + (UrlAutomata.automata.stringChecking(url) ? "passed" : "failed"));
//                    break;
//                case 3:
//                    System.out.println("Exiting program.");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter a valid choice.");
//                    break;
//            }
//        } while (choice != 3);
//        ForDoAutomata.automata.execute("for (hello < 5.3; =; >) do(");
        ForDoAutomata.automata.execute("for (hello < 5.3e+7:=var5; =; >) do*");

        scanner.close();
    }
}
