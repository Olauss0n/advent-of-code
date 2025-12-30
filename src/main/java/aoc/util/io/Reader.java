package aoc.util.io;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aoc.util.exceptions.FileNotFoundException;

public class Reader {

    public static String readInput(Class<?> callingClass) {
        return readInput(callingClass, InputType.STRING, InputFile.INPUT);
    }

    public static String readExampleInput(Class<?> callingClass) {
        return readInput(callingClass, InputType.STRING, InputFile.EXAMPLE_1);
    }

    public static String readExampleInputPartTwo(Class<?> callingClass) {
        return readInput(callingClass, InputType.STRING, InputFile.EXAMPLE_2);
    }

    private static <T> T readInput(Class<?> callingClass, InputType inputType, InputFile file) {
        // Extract package name (e.g., "aoc.day.y2024")
        String packageName = callingClass.getPackageName();
        String year = packageName.substring(packageName.lastIndexOf('.') + 1); // Extract "y2024"

        // Extract class name (e.g., "Day01")
        String className = callingClass.getSimpleName();
        String day = className.replaceAll("[^0-9]", ""); // Extract "01"

        return readInput(year, day, inputType, file);
    }

    private static <T> T readInput(String directory, String inputNumber, InputType inputType, InputFile file) {
        StringBuilder textString = new StringBuilder();
        ArrayList<String> textList = new ArrayList<>();
        String inputFile = getInputFile(directory, inputNumber, file);
        InputStream inputStream = Reader.class.getClassLoader().getResourceAsStream(inputFile);
        if (inputStream == null) {
            throw new FileNotFoundException(inputFile);
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

    private static String getInputFile(String directory, String inputNumber, InputFile file) {
        return switch (file) {
            case INPUT -> "input/%s/input-%s.txt".formatted(directory, inputNumber);
            case EXAMPLE_1 -> "input/%s/example-%s.txt".formatted(directory, inputNumber);
            case EXAMPLE_2 -> "input/%s/example-%s-2.txt".formatted(directory, inputNumber);
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

    private enum InputFile {
        INPUT,
        EXAMPLE_1,
        EXAMPLE_2
    }
}
