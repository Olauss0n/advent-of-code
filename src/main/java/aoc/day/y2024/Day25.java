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

        List<List<Integer>> lockPins = new ArrayList<>();
        for (String[][] lock : locks) {
            ArrayList<Integer> pins = new ArrayList<>();
            for (int column = 0; column < lock[0].length; column++) {
                int count = 0;
                for (int row = 1; row < lock.length; row++) {
                    if (lock[row][column].equals("#")) {
                        count += 1;
                    }
                }
                pins.add(count);
            }
            lockPins.add(pins);
        }
        List<List<Integer>> keyPins = new ArrayList<>();
        for (String[][] key : keys) {
            ArrayList<Integer> pins = new ArrayList<>();
            for (int column = 0; column < key[0].length; column++) {
                int count = 0;
                for (int row = 0; row < key.length - 1; row++) {
                    if (key[row][column].equals("#")) {
                        count += 1;
                    }
                }
                pins.add(count);
            }
            keyPins.add(pins);
        }

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
