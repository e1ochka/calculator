import java.util.Scanner;

public class ImprovedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (например, 2 + 3):");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break; // выход по команде "exit"

            try {
                double result = calculate(input);
                System.out.println("Результат: " + result);
            } catch (IllegalArgumentException | NumberFormatException e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте еще раз или введите 'exit' для выхода.");
            } catch (ArithmeticException e) {
                System.out.println("Ошибка: Деление на ноль! Попробуйте еще раз.");
            }
        }
        scanner.close();
    }

    public static double calculate(String expression) {
        String[] parts = expression.trim().split("\\s+"); // Разделение и удаление лишних пробелов

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения.");
        }

        try {
            double num1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double num2 = Double.parseDouble(parts[2]);


            switch (operator) {
                case "+": return num1 + num2;
                case "-": return num1 - num2;
                case "*": return num1 * num2;
                case "/":
                    if (num2 == 0) throw new ArithmeticException("Деление на ноль!");
                    return num1 / num2;
                default: throw new IllegalArgumentException("Неподдерживаемый оператор: " + operator);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат числа в выражении.");
        }

    }
}
