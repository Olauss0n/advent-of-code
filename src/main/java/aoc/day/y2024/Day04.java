package aoc.day.y2024;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Reader;

public class Day04 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        char[][] matrix = Converter.convertListInputToCharMatrix(inputList);

        int result = 0;
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                result += findXMAS(matrix, x, y);
            }
        }
        return result;
    }

    private int findXMAS(char[][] matrix, int x, int y) {
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
            if (isXMAS(matrix, x, y, direction[0], direction[1])) {
                count++;
            }
        }
        return count;
    }

    private boolean isXMAS(char[][] matrix, int x, int y, int xDirection, int yDirection) {
        int index = 0;
        while (isWithinBounds(matrix, x, y)) {
            char c = matrix[x][y];
            switch (index) {
                case 0 -> {
                    if (c != 'X') {
                        return false;
                    }
                }
                case 1 -> {
                    if (c != 'M') {
                        return false;
                    }
                }
                case 2 -> {
                    if (c != 'A') {
                        return false;
                    }
                }
                case 3 -> {
                    return c == 'S';
                }
            }
            x += xDirection;
            y += yDirection;
            index++;
        }
        return false;
    }

    private boolean isWithinBounds(char[][] grid, int x, int y) {
        return (x >= 0 && y >= 0 && x < grid.length && y < grid[x].length);
    }

    @Override
    public Object solvePartTwo() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        char[][] matrix = Converter.convertListInputToCharMatrix(inputList);
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

    private boolean checkMAS(char[][] matrix, int x, int y) {
        return matrix[x][y] == 'A'
                && isMS(matrix[x - 1][y - 1], matrix[x + 1][y + 1])
                && isMS(matrix[x - 1][y + 1], matrix[x + 1][y - 1]);
    }

    private boolean isMS(char first, char second) {
        return (first == 'M' && second == 'S') || (first == 'S' && second == 'M');
    }
}
