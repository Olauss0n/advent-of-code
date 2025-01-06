package aoc.day.y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.exceptions.NotImplementedException;

public class Day25 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String[][]> matrices = Arrays.stream(input.split("\n\n"))
                .map(Converter::convertInputToList)
                .map(Converter::convertListInputToStringMatrix)
                .toList();

        List<String[][]> locks = new ArrayList<>();
        List<String[][]> keys = new ArrayList<>();
        for (String[][] matrix : matrices) {
            if (matrix[0][0].equals("#")) {
                locks.add(matrix);
            } else {
                keys.add(matrix);
            }
        }

        List<List<Integer>> lockPins = getPins(locks, false);
        List<List<Integer>> keyPins = getPins(keys, true);

        int result = 0;
        for (List<Integer> lockPin : lockPins) {
            for (List<Integer> keyPin : keyPins) {
                if (!hasOverlap(lockPin, keyPin)) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private static List<List<Integer>> getPins(List<String[][]> matrices, boolean countFromTop) {
        List<List<Integer>> pinsList = new ArrayList<>();
        for (String[][] matrix : matrices) {
            List<Integer> pins = new ArrayList<>();
            for (int column = 0; column < matrix[0].length; column++) {
                int count = 0;
                int startRow = countFromTop ? 0 : 1; // Start at row 0 or row 1
                int endRow = countFromTop ? matrix.length - 1 : matrix.length; // Exclude or include last row
                for (int row = startRow; row < endRow; row++) {
                    if (matrix[row][column].equals("#")) {
                        count += 1;
                    }
                }
                pins.add(count);
            }
            pinsList.add(pins);
        }
        return pinsList;
    }

    private static boolean hasOverlap(List<Integer> lockPin, List<Integer> keyPin) {
        for (int i = 0; i < lockPin.size(); i++) {
            if (lockPin.get(i) + keyPin.get(i) >= 6) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
