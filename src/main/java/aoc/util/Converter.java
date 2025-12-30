package aoc.util;

import java.util.Arrays;
import java.util.List;

public class Converter {

    public static List<String> convertInputToList(String input) {
        return input.lines().toList();
    }

    public static List<String> convertInputToList(String input, String delimiter) {
        return Arrays.stream(input.replaceAll("\n", "").split(delimiter)).toList();
    }

    public static Matrix<String> convertInputToStringMatrix(String input) {
        return convertListInputToStringMatrix(input.lines().toList(), "");
    }

    public static Matrix<String> convertListInputToStringMatrix(List<String> input, String delimiter) {
        List<List<String>> inputMatrix = input.stream()
                .map(line -> Arrays.stream(line.trim().split(delimiter)).toList())
                .toList();

        String[][] matrix =
                new String[inputMatrix.size()][inputMatrix.getFirst().size()];

        for (int row = 0; row < inputMatrix.size(); row++) {
            for (int column = 0; column < inputMatrix.get(row).size(); column++) {
                matrix[row][column] = inputMatrix.get(row).get(column);
            }
        }
        return new Matrix<>(matrix);
    }

    public static Matrix<String> convertListInputToStringMatrix(List<String> input, int amountOfRows) {
        String[][] grid = new String[input.size() / amountOfRows][amountOfRows];
        for (int i = 0; i < input.size(); i++) {
            grid[i / amountOfRows][i % amountOfRows] = input.get(i);
        }
        return new Matrix<>(grid);
    }

    public static Matrix<String> convertListInputToStringMatrixWithoutTrim(List<String> input) {
        String[][] matrix = new String[input.size()][input.getFirst().length()];
        for (int line = 0; line < input.size(); line++) {
            for (int index = 0; index < matrix[line].length; index++) {
                matrix[line][index] = String.valueOf(input.get(line).charAt(index));
            }
        }
        return new Matrix<>(matrix);
    }
}
