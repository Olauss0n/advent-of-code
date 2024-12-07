package aoc.util;

import java.util.Arrays;
import java.util.List;

public class Converter {

    public static String[][] convertListInputToStringMatrix(List<String> input) {
        return convertListInputToStringMatrix(input, "");
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
