import javax.swing.*;
import java.awt.*;

public class LayoutDemo extends JFrame {

    public LayoutDemo() {
        setTitle("Layout Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // BorderLayout for the main frame

        //JPanel с FlowLayout
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout()); // FlowLayout for the panel

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        flowPanel.add(button1);
        flowPanel.add(button2);
        flowPanel.add(button3);
        flowPanel.add(button4);

        add(flowPanel, BorderLayout.CENTER); // Add the panel to the center of the frame

        // Добавим еще один компонент в южную часть BorderLayout главного фрейма
        JLabel label = new JLabel("Это надпись в южной части");
        add(label, BorderLayout.SOUTH);

        setVisible(true);
    }


    public static void main(String[] args) {
        new LayoutDemo();
    }
}
