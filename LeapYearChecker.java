import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearChecker {

    public static boolean isLeapYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Год должен быть положительным числом.");
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year;

        System.out.println("Введите год:");

        try {
            year = scanner.nextInt();
            if (isLeapYear(year)) {
                System.out.println(year + " - високосный год.");
            } else {
                System.out.println(year + " - не високосный год.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введен некорректный формат года. Пожалуйста, введите целое число.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


