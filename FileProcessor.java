import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileProcessor {

    public static void removeDuplicates(String inputFile, String outputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        Set<String> seenLines = new HashSet<>();
        int duplicateCount = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            if (!seenLines.contains(line)) {
                writer.write(line);
                writer.newLine();
                seenLines.add(line);
            } else {
                duplicateCount++;
            }
        }

        writer.write("Удалено дубликатов: " + duplicateCount);
        writer.close();
        reader.close();
    }


    public static void restoreCompressedFile(String compressedFile, String restoredFile) throws IOException {
        //  В этом примере "сжатие" - это просто удаление дубликатов.  Восстановление - это копирование файла.
        // Для реальной компрессии понадобится библиотека, например, java.util.zip
        BufferedReader reader = new BufferedReader(new FileReader(compressedFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(restoredFile));
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.startsWith("Удалено дубликатов:")){
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();
    }


    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        String restoredFileName = "restored.txt";

        try {
            // Заполним input.txt для теста
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFileName));
            writer.write("Строка 1"); writer.newLine();
            writer.write("Строка 2"); writer.newLine();
            writer.write("Строка 1"); writer.newLine();
            writer.write("Строка 3"); writer.newLine();
            writer.write("Строка 2"); writer.newLine();
            writer.close();

            removeDuplicates(inputFileName, outputFileName);
            System.out.println("Дубликаты удалены. Результат записан в " + outputFileName);

            restoreCompressedFile(outputFileName, restoredFileName);
            System.out.println("Восстановленный файл записан в " + restoredFileName);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}

