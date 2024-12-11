package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Reader;

public class Day10 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne() {
        return commonPart(false);
    }

    @Override
    public Object solvePartTwo() {
        return commonPart(true);
    }

    private Object commonPart(boolean isPartTwo) {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);
        List<Position> zeros = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("0")) {
                    zeros.add(new Position(row, col));
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
        if (matrix[pos.row][pos.col].equals("9")) {
            visited.add(pos);
            return visited;
        }
        for (Direction direction : Direction.values()) {
            Position newPos = pos.move(direction);
            if (isWithinBound(matrix, newPos) && matrix[newPos.row][newPos.col].equals(String.valueOf(value))) {
                visited = checkAdjacentPositions(matrix, newPos, value + 1, visited);
            }
        }
        return visited;
    }

    private boolean isWithinBound(String[][] matrix, Position pos) {
        return (pos.col >= 0 && pos.row >= 0 && pos.row < matrix.length && pos.col < matrix[0].length);
    }

    private record Position(int row, int col) {

        private Position move(Direction direction) {
            return switch (direction) {
                case UP -> new Position(row - 1, col);
                case DOWN -> new Position(row + 1, col);
                case LEFT -> new Position(row, col - 1);
                case RIGHT -> new Position(row, col + 1);
            };
        }
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
