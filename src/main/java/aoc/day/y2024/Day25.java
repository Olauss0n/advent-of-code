package aoc.day.y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.exception.NotImplementedException;
import aoc.util.grid.Matrix;
import aoc.util.grid.Position;
import aoc.util.parse.Parser;

public class Day25 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<Matrix<String>> matrices =
                Arrays.stream(input.split("\n\n")).map(Parser::toStringMatrix).toList();

        List<Matrix<String>> locks = new ArrayList<>();
        List<Matrix<String>> keys = new ArrayList<>();
        for (Matrix<String> matrix : matrices) {
            if (matrix.get(new Position(0, 0)).equals("#")) {
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

    private static List<List<Integer>> getPins(List<Matrix<String>> matrices, boolean countFromTop) {
        List<List<Integer>> pinsList = new ArrayList<>();
        for (Matrix<String> matrix : matrices) {
            List<Integer> pins = new ArrayList<>();
            for (int column = 0; column < matrix.columns(); column++) {
                int count = 0;
                int startRow = countFromTop ? 0 : 1; // Start at row 0 or row 1
                int endRow = countFromTop ? matrix.rows() - 1 : matrix.rows(); // Exclude or include last row
                for (int row = startRow; row < endRow; row++) {
                    if (matrix.get(new Position(column, row)).equals("#")) {
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
