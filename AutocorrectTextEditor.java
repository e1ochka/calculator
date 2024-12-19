import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AutocorrectTextEditor extends JFrame {

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem, saveItem, exitItem;
    private JFileChooser fileChooser;
    private Map<String, String> autocorrectMap; // Словарь автозамены
    private boolean autocorrectEnabled = true; // Флаг включения автозамены

    public AutocorrectTextEditor() {
        // ... (код из предыдущего примера остается без изменений) ...

        autocorrectMap = new HashMap<>();
        autocorrectMap.put("teh", "the");
        autocorrectMap.put("whr", "where");
        autocorrectMap.put("wer", "were");


        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ' && autocorrectEnabled) {
                    new Thread(() -> autocorrect()).start();
                }
            }
        });

        setVisible(true);
    }


    private void autocorrect() {
        int caretPosition = textArea.getCaretPosition();
        try {
            int start = findWordStart(caretPosition);
            int end = findWordEnd(caretPosition);
            String word = textArea.getText(start, end - start);

            if (autocorrectMap.containsKey(word)) {
                String correctWord = autocorrectMap.get(word);
                textArea.replaceRange(correctWord, start, end);
                textArea.setCaretPosition(start + correctWord.length());
            }
        } catch (BadLocationException e) {
            //Обработка исключений
            System.err.println("Ошибка автозамены: " + e.getMessage());
        }
    }


    private int findWordStart(int pos) throws BadLocationException {
        int start = pos -1;
        while (start >= 0 && Character.isLetter(textArea.getDocument().getText(start, 1).charAt(0))){
            start--;
        }
        return start + 1;
    }

    private int findWordEnd(int pos) throws BadLocationException {
        int end = pos;
        while (end < textArea.getDocument().getLength() && Character.isLetter(textArea.getDocument().getText(end, 1).charAt(0))){
            end++;
        }
        return end;
    }

    // ... (остальной код из предыдущего примера остается без изменений) ...
}

