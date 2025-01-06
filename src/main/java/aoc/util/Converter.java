package aoc.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {

    public static List<String> convertInputToList(String input) {
        return input.lines().toList();
    }

    public static String[][] convertInputToStringMatrix(String input) {
        return convertListInputToStringMatrix(input.lines().toList());
    }

    public static String[][] convertListInputToStringMatrix(List<String> input) {
        return convertListInputToStringMatrix(input, "");
    }

    public static List<List<String>> convertListInputToListMatrix(List<String> input, int amountOfRows) {
        List<List<String>> matrix = new ArrayList<>();
        ArrayList<String> subList = new ArrayList<>();
        for (String line : input) {
            subList.add(line);
            if (amountOfRows == subList.size()) {
                matrix.add(new ArrayList<>(subList));
                subList.clear();
            }
        }
        return matrix;
    }

    public static String[][] convertListInputToStringMatrix(List<String> input, String delimiter) {
        List<List<String>> inputMatrix = input.stream()
                .map(line -> Arrays.stream(line.split(delimiter)).toList())
                .toList();

        String[][] matrix =
                new String[inputMatrix.size()][inputMatrix.getFirst().size()];

        for (int row = 0; row < inputMatrix.size(); row++) {
            for (int column = 0; column < inputMatrix.get(row).size(); column++) {
                matrix[row][column] = inputMatrix.get(row).get(column);
            }
        }
        return matrix;
    }

    public static char[][] convertListInputToCharMatrix(List<String> input) {
        char[][] matrix = new char[input.size()][input.getFirst().length()];

        for (int i = 0; i < input.size(); i++) {
            matrix[i] = input.get(i).toCharArray();
        }

        return matrix;
    }
}
