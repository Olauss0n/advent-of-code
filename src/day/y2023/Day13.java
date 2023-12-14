package day.y2023;

import util.Reader;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day13 {

    public static void run() {
        System.out.println("For part one: ");
        runCommonPart(0);
        System.out.println("For part two: ");
        runCommonPart(1);
    }

    private static void runCommonPart(int targetDifference) {
        List<String> input = Arrays.stream(
                        Reader.readInputAsString("y2023", "13").split("\n\n"))
                .toList();

        Integer sum = 0;
        for (String inputLine : input) {
            String[] pattern = inputLine.split("\n");
            String[][] matrix = new String[pattern.length][pattern[0].length()];
            for (int i = 0; i < pattern.length; i++) {
                String[] patternSplit = pattern[i].split("");
                for (int j = 0; j < patternSplit.length; j++) {
                    matrix[i][j] = patternSplit[j];
                }
            }

            sum += reflect(matrix, targetDifference);
        }
        System.out.println(sum);
    }

    private static Integer reflect(String[][] matrix, int targetDifference) {
        int difference;
        for (int column = 1; column < matrix[0].length; column++) {
            difference = 0;
            for (int j = 1; j < matrix[0].length; j++) {
                if (column - j < 0 || column + (j - 1) > matrix[0].length - 1) {
                    break;
                }
                for (String[] strings : matrix) {
                    String a = strings[column - j];
                    String b = strings[column + j - 1];
                    if (!Objects.equals(a, b)) {
                        difference++;
                    }
                }
            }
            if (difference == targetDifference) {
                return column;
            }
        }

        for (int row = 1; row < matrix.length; row++) {
            difference = 0;
            for (int i = 1; i < matrix.length; i++) {
                if ((row - i) < 0 || row + (i - 1) > matrix.length - 1) {
                    break;
                }
                for (int j = 0; j < matrix[i].length; j++) {
                    String a = matrix[row - i][j];
                    String b = matrix[row + (i - 1)][j];
                    if (!Objects.equals(a, b)) {
                        difference++;
                    }
                }
            }
            if (difference == targetDifference) {
                return row * 100;
            }
        }
        return 0;
    }
}
