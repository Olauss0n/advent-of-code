package aoc.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public static String readInputAsString(Class<?> callingClass) {
        return readInput(callingClass, InputType.STRING);
    }

    public static List<String> readInputAsList(Class<?> callingClass) {
        return readInput(callingClass, InputType.LIST);
    }

    private static <T> T readInput(Class<?> callingClass, InputType inputType) {
        // Extract package name (e.g., "aoc.day.y2024")
        String packageName = callingClass.getPackageName();
        String year = packageName.substring(packageName.lastIndexOf('.') + 1); // Extract "y2024"

        // Extract class name (e.g., "Day01")
        String className = callingClass.getSimpleName();
        String day = className.replaceAll("[^0-9]", ""); // Extract "01"

        // Delegate to existing read method
        return switch (inputType) {
            case STRING -> (T) readInputAsString(year, day);
            case LIST -> (T) readInputAsList(year, day);
        };
    }

    private static String readInputAsString(String directory, String inputNumber) {
        StringBuilder textFile = new StringBuilder();
        InputStream inputStream = Reader.class
                .getClassLoader()
                .getResourceAsStream("input/%s/input-%s.txt".formatted(directory, inputNumber));
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            textFile.append(data).append("\n");
        }
        scanner.close();
        return textFile.toString();
    }

    private static List<String> readInputAsList(String directory, String inputNumber) {
        ArrayList<String> textFile = new ArrayList<>();
        InputStream inputStream = Reader.class
                .getClassLoader()
                .getResourceAsStream("input/%s/input-%s.txt".formatted(directory, inputNumber));
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            textFile.add(data);
        }
        scanner.close();
        return textFile;
    }
}
