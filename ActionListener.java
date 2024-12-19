import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0;
    private String operator = "";
    private boolean newNumber = true;


    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
            if (newNumber) {
                display.setText(command);
                newNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("=")) {
            double num2 = Double.parseDouble(display.getText());
            double result = calculate(num1, num2, operator);
            display.setText(String.valueOf(result));
            newNumber = true;
        } else {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            newNumber = true;
        }
    }


    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 == 0) {
                    //Обработка деления на ноль.  В реальном приложении нужна более сложная обработка.
                    return 0; // Или выбросить исключение
                }
                return num1 / num2;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
