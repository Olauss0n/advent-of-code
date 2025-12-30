package aoc.day.y2023;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import aoc.day.AdventOfCodeSolver;

public class Day13 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return runCommonPart(input, 0);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return runCommonPart(input, 1);
    }

    private int runCommonPart(String input, int targetDifference) {
        List<String> inputList = Arrays.stream(input.split("\n\n")).toList();
        Integer sum = 0;
        for (String inputLine : inputList) {
            String[] pattern = inputLine.split("\n");
            String[][] matrix = new String[pattern.length][pattern[0].length()];
            for (int i = 0; i < pattern.length; i++) {
                String[] patternSplit = pattern[i].split("");
                System.arraycopy(patternSplit, 0, matrix[i], 0, patternSplit.length);
            }
            sum += reflect(matrix, targetDifference);
        }
        return sum;
    }

    private static Integer reflect(String[][] matrix, int targetDifference) {
        for (int column = 1; column < matrix[0].length; column++) {
            int difference = 0;
            for (int j = 1; j < matrix[0].length; j++) {
                if (column - j < 0 || column + j > matrix[0].length) {
                    break;
                }
                for (int i = 0; i < matrix.length; i++) {
                    String leftReflection = matrix[i][column - j];
                    String rightReflection = matrix[i][column + j - 1];
                    if (!Objects.equals(leftReflection, rightReflection)) {
                        difference++;
                    }
                }
            }
            if (difference == targetDifference) {
                return column;
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            int difference = 0;
            for (int i = 1; i < matrix.length; i++) {
                if (row - i < 0 || row + i > matrix.length) {
                    break;
                }
                for (int j = 0; j < matrix[i].length; j++) {
                    String topReflection = matrix[row - i][j];
                    String bottomReflection = matrix[row + (i - 1)][j];
                    if (!Objects.equals(topReflection, bottomReflection)) {
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
