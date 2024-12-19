import java.util.Scanner;

public class CompoundInterestCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите действие:");
        System.out.println("1. Вычислить сложный процент");
        System.out.println("2. Вычислить необходимую процентную ставку");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    calculateCompoundInterest(scanner);
                    break;
                case 2:
                    calculateInterestRate(scanner);
                    break;
                default:
                    System.out.println("Неверный выбор действия.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }


    public static void calculateCompoundInterest(Scanner scanner) {
        System.out.println("Введите начальную сумму:");
        double principal = scanner.nextDouble();

        System.out.println("Введите процентную ставку (в процентах):");
        double rate = scanner.nextDouble();

        System.out.println("Введите количество периодов:");
        int periods = scanner.nextInt();

        if (principal <= 0 || rate < 0 || periods <= 0) {
            throw new IllegalArgumentException("Некорректные входные данные.");
        }

        double amount = principal * Math.pow(1 + rate / 100, periods);
        System.out.println("Итоговая сумма: " + amount);
    }


    public static void calculateInterestRate(Scanner scanner) {
        System.out.println("Введите начальную сумму:");
        double principal = scanner.nextDouble();

        System.out.println("Введите целевую сумму:");
        double targetAmount = scanner.nextDouble();

        System.out.println("Введите количество периодов:");
        int periods = scanner.nextInt();


        if (principal <= 0 || targetAmount <= principal || periods <= 0) {
            throw new IllegalArgumentException("Некорректные входные данные.");
        }

        double rate = 100 * (Math.pow(targetAmount / principal, 1.0 / periods) - 1);
        System.out.println("Необходимая процентная ставка: " + String.format("%.2f", rate) + "%");
    }
}

