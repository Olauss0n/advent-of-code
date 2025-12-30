package aoc.day.y2024;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil.OctagonalDirection;
import aoc.util.GridUtil.Position;
import aoc.util.Matrix;

public class Day04 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        int result = 0;
        for (int y = 0; y < matrix.rows(); y++) {
            for (int x = 0; x < matrix.columns(); x++) {
                result += findXMAS(matrix, new Position(x, y));
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);
        int result = 0;
        for (int y = 1; y < matrix.rows() - 1; y++) {
            for (int x = 1; x < matrix.columns() - 1; x++) {
                if (checkMAS(matrix, new Position(x, y))) {
                    result++;
                }
            }
        }
        return result;
    }

    private int findXMAS(Matrix<String> matrix, Position position) {
        int count = 0;
        for (OctagonalDirection direction : OctagonalDirection.values()) {
            if (isXMAS(matrix, position, direction)) {
                count++;
            }
        }
        return count;
    }

    private boolean isXMAS(Matrix<String> matrix, Position position, OctagonalDirection direction) {
        int index = 0;
        while (matrix.isWithinBounds(position)) {
            String c = matrix.get(position);
            switch (index) {
                case 0 -> {
                    if (!c.equals("X")) {
                        return false;
                    }
                }
                case 1 -> {
                    if (!c.equals("M")) {
                        return false;
                    }
                }
                case 2 -> {
                    if (!c.equals("A")) {
                        return false;
                    }
                }
                case 3 -> {
                    return c.equals("S");
                }
            }
            position = position.move1Step(direction);
            index++;
        }
        return false;
    }

    private boolean checkMAS(Matrix<String> matrix, Position position) {
        return matrix.get(position).equals("A")
                && isMS(
                        matrix.get(position.move1Step(OctagonalDirection.NORTH_WEST)),
                        matrix.get(position.move1Step(OctagonalDirection.SOUTH_EAST)))
                && isMS(
                        matrix.get(position.move1Step(OctagonalDirection.SOUTH_WEST)),
                        matrix.get(position.move1Step(OctagonalDirection.NORTH_EAST)));
    }

    private boolean isMS(String first, String second) {
        return (first.equals("M") && second.equals("S")) || (first.equals("S") && second.equals("M"));
    }
}
