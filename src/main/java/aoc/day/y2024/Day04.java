package aoc.day.y2024;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil.Position;

import static aoc.util.MatrixUtil.isWithinBounds;

public class Day04 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input) {
        List<String> inputList = Converter.convertInputToList(input);
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);

        int result = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                result += findXMAS(matrix, new Position(x, y));
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input) {
        List<String> inputList = Converter.convertInputToList(input);
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);
        int result = 0;
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (checkMAS(matrix, x, y)) {
                    result++;
                }
            }
        }
        return result;
    }

    private int findXMAS(String[][] matrix, Position position) {
        int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
            {1, 1},
            {-1, 1},
            {-1, -1},
            {1, -1}
        };
        int count = 0;
        for (int[] direction : directions) {
            if (isXMAS(matrix, position, direction[0], direction[1])) {
                count++;
            }
        }
        return count;
    }

    private boolean isXMAS(String[][] matrix, Position position, int xDirection, int yDirection) {
        int index = 0;
        while (isWithinBounds(matrix, position)) {
            String c = matrix[position.yPos()][position.xPos()];
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
            position = position.move(xDirection, yDirection);
            index++;
        }
        return false;
    }

    private boolean checkMAS(String[][] matrix, int x, int y) {
        return matrix[x][y].equals("A")
                && isMS(matrix[x - 1][y - 1], matrix[x + 1][y + 1])
                && isMS(matrix[x - 1][y + 1], matrix[x + 1][y - 1]);
    }

    private boolean isMS(String first, String second) {
        return (first.equals("M") && second.equals("S")) || (first.equals("S") && second.equals("M"));
    }
}
