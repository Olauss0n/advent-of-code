package aoc.day.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Matrix;

public class Day06 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        List<String> operations =
                Arrays.stream(inputList.getLast().trim().split("\\s+")).toList();
        Matrix<String> matrix =
                Converter.convertListInputToStringMatrix(inputList.subList(0, inputList.size() - 1), "\\s+");
        long result = 0;
        int index = 0;
        for (int col = 0; col < matrix.columns(); col++) {
            List<Long> problems = new ArrayList<>();
            for (int row = 0; row < matrix.rows(); row++) {
                problems.add(Long.parseLong(matrix.get(col, row)));
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
        List<String> operations = Arrays.asList(inputList.getLast().trim().split("\\s+"));
        Matrix<String> matrix =
                Converter.convertListInputToStringMatrixWithoutTrim(inputList.subList(0, inputList.size() - 1));

        long result = 0;
        int operationIndex = operations.size() - 1;
        List<Long> problemNumbers = new ArrayList<>();
        List<List<String>> columns = matrix.getColumns();

        for (int columnIndex = columns.size() - 1; columnIndex >= 0; columnIndex--) {
            List<String> column = columns.get(columnIndex);
            String verticalNumber = String.join("", column);
            if (verticalNumber.isBlank()) {
                result += performOperation(problemNumbers, operations.get(operationIndex));
                problemNumbers.clear();
                operationIndex -= 1;
            } else {
                problemNumbers.add(Long.parseLong(verticalNumber.replaceAll(" ", "")));
            }
        }
        if (!problemNumbers.isEmpty()) {
            result += performOperation(problemNumbers, operations.get(operationIndex));
        }
        return result;
    }

    private long performOperation(List<Long> numbers, String operation) {
        if (operation.equals("+")) {
            return numbers.stream().mapToLong(Long::longValue).sum();
        } else {
            return numbers.stream().reduce(1L, (a, b) -> a * b);
        }
    }
}
