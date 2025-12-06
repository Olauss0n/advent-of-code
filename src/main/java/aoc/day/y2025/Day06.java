package aoc.day.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day06 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        List<String> operations =
                Arrays.stream(inputList.getLast().trim().split("\\s+")).toList();
        String[][] matrix =
                Converter.convertListInputToStringMatrix(inputList.subList(0, inputList.size() - 1), "\\s+");
        long result = 0;
        int index = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            List<Long> problems = new ArrayList<>();
            for (int row = 0; row < matrix.length; row++) {
                problems.add(Long.parseLong(matrix[row][col]));
            }
            if (operations.get(index).equals("+")) {
                result += problems.stream().reduce(0L, Long::sum);
            } else {
                result += problems.stream().reduce(1L, (a, b) -> a * b);
            }
            index += 1;
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        List<String> operations =
                Arrays.stream(inputList.getLast().trim().split("\\s+")).toList();
        char[][] charMatrix = Converter.convertListInputToCharMatrix(inputList.subList(0, inputList.size() - 1));
        long result = 0;
        int index = 0;
        List<Long> problems = new ArrayList<>();
        for (int col = 0; col < charMatrix[0].length; col++) {
            boolean columnEmpty = true;
            List<String> problemNumbers = new ArrayList<>();
            for (int row = 0; row < charMatrix.length; row++) {
                if (charMatrix[row][col] != ' ') {
                    problemNumbers.add(String.valueOf(charMatrix[row][col]));
                    columnEmpty = false;
                }
            }
            if (!problemNumbers.isEmpty()) {
                Long problemNumber = Long.parseLong(String.join("", problemNumbers));
                problems.add(problemNumber);
            }
            if (columnEmpty || col == charMatrix[0].length - 1) {
                if (operations.get(index).equals("+")) {
                    result += problems.stream().reduce(Long::sum).orElse(0L);
                } else {
                    result += problems.stream().reduce((a, b) -> a * b).orElse(0L);
                }
                index += 1;
                problems = new ArrayList<>();
            }
        }
        return result;
    }
}
