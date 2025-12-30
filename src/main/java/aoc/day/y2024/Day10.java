package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.grid.Direction;
import aoc.util.grid.Matrix;
import aoc.util.grid.Position;

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
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);
        List<Position> zeros = new ArrayList<>();
        for (int row = 0; row < matrix.rows(); row++) {
            for (int col = 0; col < matrix.columns(); col++) {
                Position position = new Position(col, row);
                if (matrix.get(position).equals("0")) {
                    zeros.add(position);
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

    private List<Position> checkAdjacentPositions(
            Matrix<String> matrix, Position pos, int value, List<Position> visited) {
        if (matrix.get(pos).equals("9")) {
            visited.add(pos);
            return visited;
        }
        for (Direction direction : Direction.values()) {
            Position newPos = pos.move(direction);
            if (matrix.isWithinBounds(newPos) && matrix.get(newPos).equals(String.valueOf(value))) {
                visited = checkAdjacentPositions(matrix, newPos, value + 1, visited);
            }
        }
        return visited;
    }
}
