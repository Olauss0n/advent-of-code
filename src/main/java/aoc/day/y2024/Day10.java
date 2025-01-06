package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil;
import aoc.util.MatrixUtil.Direction;
import aoc.util.MatrixUtil.Position;

public class Day10 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return commonPart(input, false);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return commonPart(input, true);
    }

    private Object commonPart(String input, boolean isPartTwo) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);
        List<Position> zeros = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("0")) {
                    zeros.add(new Position(col, row));
                }
            }
        }
        int counter = 0;
        for (Position zero : zeros) {
            List<Position> positions = checkAdjacentPositions(matrix, zero, 1, new ArrayList<>());
            if (!isPartTwo) {
                counter += new HashSet<>(positions).size();
            } else {
                counter += positions.size();
            }
        }
        return counter;
    }

    private List<Position> checkAdjacentPositions(String[][] matrix, Position pos, int value, List<Position> visited) {
        if (matrix[pos.yPos()][pos.xPos()].equals("9")) {
            visited.add(pos);
            return visited;
        }
        for (Direction direction : Direction.values()) {
            Position newPos = pos.move(direction);
            if (MatrixUtil.isWithinBounds(matrix, newPos)
                    && matrix[newPos.yPos()][newPos.xPos()].equals(String.valueOf(value))) {
                visited = checkAdjacentPositions(matrix, newPos, value + 1, visited);
            }
        }
        return visited;
    }
}
