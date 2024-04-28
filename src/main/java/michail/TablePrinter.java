package michail;

import java.util.Vector;

public class TablePrinter {
    public TablePrinter() {
    }

    public static void printTable(Vector<String> headers, Vector<Vector<String>> data) {
        // Вычисление максимальной длины каждого столбца
        int[] maxLengths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            maxLengths[i] = headers.get(i).length();
            for (Vector<String> row : data) {
                if (row.get(i).length() > maxLengths[i]) {
                    maxLengths[i] = row.get(i).length();
                }
            }
        }

        // Построение формата строки с учётом максимальных длин
        StringBuilder format = new StringBuilder("|");
        for (int len : maxLengths) {
            format.append(" %-").append(len).append("s |");
        }
        format.append("\n");

        printDivider(maxLengths);
        // Печать заголовка
        System.out.printf(format.toString(), headers.toArray());
        // Печать разделителя
        printDivider(maxLengths);

        // Печать строк данных
        for (Vector<String> row : data) {
            System.out.printf(format.toString(), row.toArray());
            printDivider(maxLengths);
        }
    }

    private static void printDivider(int[] lengths) {
        System.out.print("+");
        for (int length : lengths) {
            for (int i = 0; i < length + 2; i++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }
}