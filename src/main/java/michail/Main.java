package michail;

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class Main {
    public static String readFileToString(String filePath) {
        try {
            Path path = Path.of(filePath);
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("----------------");
            System.out.println("Menu:");
            System.out.println("1. file1.txt");
            System.out.println("2. file2.txt");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Проверяем, является ли следующий ввод целым числом
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Переходим на следующую строку
            } else {
                System.out.println("Invalid choice. Please enter a valid choice.");
                scanner.nextLine(); // Переходим на следующую строку
                continue; // Пропускаем оставшуюся часть итерации цикла
            }
            String path = "";


            switch (choice) {
                case 1:
                    path = "src/main/resources/file1.txt";
                    break;
                case 2:
                    path = "src/main/resources/file2.txt";
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
                    break;
            }
            String input = readFileToString(path);
            ForDoAutomata.automata.execute(input);
        } while (choice != 3);
//        ForDoAutomata.automata.execute("for (hello < 5.3; =; >) do(");


        scanner.close();
    }
}
