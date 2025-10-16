import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Клас Lab1Hnatyshyn305 реалізує програму для лабораторної роботи №1.
 * Програма генерує зубчастий масив згідно з індивідуальним варіантом,
 * виводить його на екран та зберігає у текстовий файл.
 *
 * @author Hnatyshyn Bohdan
 * @version 1.1
 */
public class Lab1Hnatyshyn305 {

    /**
     * Головний метод, який є точкою входу в програму.
     * Він відповідає за взаємодію з користувачем, зчитування вхідних даних
     * та виклик методів для генерації та виводу матриці.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введіть розмір матриці: ");
        int size = sc.nextInt();

        System.out.println("Введіть символ заповнювач: ");
        String symbolStr = sc.next();

        if (symbolStr.length() != 1) {
            System.out.println("Введіть коректний символ заповнювач");
            return;
        }
        char symbol = symbolStr.charAt(0);

        String fileName = "Lab1.txt";

        try {
            char[][] matrix = generateMatrix(size, symbol);
            printAndSaveMatrix(matrix, fileName);
        } catch (IOException e) {
            System.err.println("Сталася помилка під час запису в файл: " + e.getMessage());
        }
    }

    /**
     * Генерує та заповнює зубчастий масив символами згідно з варіантом №7.
     * Розмір кожного рядка розраховується на основі симетричної логіки.
     *
     * @param size   Базовий розмір (висота) матриці.
     * @param symbol Символ, яким буде заповнено масив.
     * @return Сформований та заповнений двовимірний зубчастий масив символів.
     */
    public static char[][] generateMatrix(int size, char symbol) {
        char[][] arr = new char[size][];

        for (int i = 0; i < size; i++) {
            // Розрахунок довжини рядка для створення симетричної фігури
            int rowLength = Math.min(i, size - 1 - i) + 1;
            arr[i] = new char[rowLength];

            // Заповнення рядка символами
            for (int j = 0; j < rowLength; j++) {
                arr[i][j] = symbol;
            }
        }
        return arr;
    }

    /**
     * Виводить згенеровану матрицю в консоль та зберігає її у вказаний файл.
     * Кожен рядок матриці записується в новий рядок у файлі.
     *
     * @param matrix Зубчастий масив, який потрібно вивести та зберегти.
     * @param file   Назва файлу для збереження матриці.
     * @throws IOException Якщо виникає помилка під час запису у файл.
     */
    public static void printAndSaveMatrix(char[][] matrix, String file) throws IOException {
        System.out.println("Результат матриці: ");
        try (FileWriter writer = new FileWriter(file)) {
            for (char[] row : matrix) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                    writer.write(cell + " ");
                }
                System.out.println();
                writer.write("\n");
            }
        }
    }
}
