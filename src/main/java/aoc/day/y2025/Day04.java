package aoc.day.y2025;

import java.util.ArrayList;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil;
import aoc.util.MatrixUtil.OctagonalDirection;
import aoc.util.MatrixUtil.Position;

public class Day04 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);
        int result = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                int occurrences = 0;
                Position currentPosition = new Position(column, row);
                if (matrix[row][column].equals("@")) {
                    for (OctagonalDirection direction : MatrixUtil.OctagonalDirection.values()) {
                        Position newPosition = currentPosition.move1Step(direction);
                        if (MatrixUtil.isWithinBounds(matrix, newPosition)) {
                            if (matrix[newPosition.yPos()][newPosition.xPos()].equals("@")) {
                                occurrences += 1;
                            }
                        }
                    }
                    if (occurrences < 4) {
                        result += 1;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);
        int result = 0;
        int removals;
        do {
            List<Position> positions = new ArrayList<>();
            for (int row = 0; row < matrix.length; row++) {
                for (int column = 0; column < matrix[row].length; column++) {
                    int occurrences = 0;
                    Position currentPosition = new Position(column, row);
                    if (matrix[row][column].equals("@")) {
                        for (OctagonalDirection direction : MatrixUtil.OctagonalDirection.values()) {
                            Position newPosition = currentPosition.move1Step(direction);
                            if (MatrixUtil.isWithinBounds(matrix, newPosition)) {
                                if (matrix[newPosition.yPos()][newPosition.xPos()].equals("@")) {
                                    occurrences += 1;
                                }
                            }
                        }
                        if (occurrences < 4) {
                            positions.add(currentPosition);
                        }
                    }
                }
            }
            for (Position position : positions) {
                matrix[position.yPos()][position.xPos()] = ".";
            }
            removals = positions.size();
            result += removals;
        } while (removals != 0);
        return result;
    }
}
