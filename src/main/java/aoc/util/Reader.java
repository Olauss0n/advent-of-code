package aoc.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public static String readInputAsSingleString(Class<?> callingClass) {
        return readInput(callingClass, InputType.STRING).toString().replace("\n", "");
    }

    public static String readInputAsString(Class<?> callingClass) {
        return readInput(callingClass, InputType.STRING);
    }

    public static List<String> readInputAsList(Class<?> callingClass) {
        return readInput(callingClass, InputType.LIST);
    }

    public static List<List<String>> readInputAsMatrix(Class<?> callingClass, int amountOfRows) {
        InputType.MATRIX.setAmountOfRows(amountOfRows);
        return readInput(callingClass, InputType.MATRIX);
    }

    private static <T> T readInput(Class<?> callingClass, InputType inputType) {
        // Extract package name (e.g., "aoc.day.y2024")
        String packageName = callingClass.getPackageName();
        String year = packageName.substring(packageName.lastIndexOf('.') + 1); // Extract "y2024"

        // Extract class name (e.g., "Day01")
        String className = callingClass.getSimpleName();
        String day = className.replaceAll("[^0-9]", ""); // Extract "01"

        return readInput(year, day, inputType);
    }

    private static <T> T readInput(String directory, String inputNumber, InputType inputType) {
        StringBuilder textString = new StringBuilder();
        ArrayList<String> textList = new ArrayList<>();
        String inputFile = "input/%s/input-%s.txt".formatted(directory, inputNumber);
        InputStream inputStream = Reader.class.getClassLoader().getResourceAsStream(inputFile);
        if (inputStream == null) {
            throw new RuntimeException("Could not find input file: " + inputFile);
        }
        Scanner scanner = new Scanner(inputStream);
        List<List<String>> matrix = new ArrayList<>();
        switch (inputType) {
            case MATRIX -> {
                List<String> group = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    group.add(scanner.nextLine());
                    if (group.size() == inputType.amountOfRows) {
                        matrix.add(new ArrayList<>(group));
                        group.clear();
                    }
                }
            }
            case LIST, STRING -> {
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    textString.append(data).append("\n");
                    textList.add(data);
                }
            }
        }
        scanner.close();
        return switch (inputType) {
            case STRING -> (T) textString.toString();
            case LIST -> (T) textList;
            case MATRIX -> (T) matrix;
        };
    }

    private enum InputType {
        STRING,
        LIST,
        MATRIX;

        public void setAmountOfRows(int amountOfRows) {
            this.amountOfRows = amountOfRows;
        }

        private int amountOfRows;
    }
}
