package aoc.day.y2024;

import java.util.HashSet;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Reader;

public class Day06 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);

        Position startPosition = getStartPosition(matrix);
        HashSet<Position> visited = findPositions(startPosition, matrix);
        return visited.size();
    }

    private Position getStartPosition(String[][] matrix) {
        Position startPosition = null;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("^")) {
                    startPosition = new Position(row, col);
                }
            }
        }
        if (startPosition == null) {
            throw new RuntimeException("No start position found");
        }
        return startPosition;
    }

    private boolean isOutOfBounds(Position position, Direction direction, String[][] matrix) {
        return position.move(direction).row < 0
                || position.move(direction).row >= matrix.length
                || position.move(direction).col < 0
                || position.move(direction).col >= matrix[0].length;
    }

    @Override
    public Object solvePartTwo() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);

        Position startPosition = getStartPosition(matrix);
        HashSet<Position> visited = findPositions(startPosition, matrix);

        int loops = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!matrix[row][col].equals(".")) {
                    continue;
                }
                if (!visited.contains(new Position(row, col))) {
                    continue;
                }
                if (startPosition.row == row && startPosition.col == col) {
                    continue;
                }
                matrix[row][col] = "#";
                if (isLoop(startPosition, matrix)) {
                    loops++;
                }
                matrix[row][col] = ".";
            }
        }
        return loops;
    }

    private HashSet<Position> findPositions(Position currentPosition, String[][] matrix) {
        HashSet<Position> visited = new HashSet<>();
        Direction currentDirection = Direction.UP;
        while (true) {
            visited.add(currentPosition);
            if (isOutOfBounds(currentPosition, currentDirection, matrix)) {
                break;
            }
            while (matrix[currentPosition.move(currentDirection).row][currentPosition.move(currentDirection).col]
                    .equals("#")) {
                currentDirection = currentDirection.rotate();
            }
            currentPosition = currentPosition.move(currentDirection);
        }
        return visited;
    }

    private boolean isLoop(Position currentPosition, String[][] matrix) {
        HashSet<Pair> visited = new HashSet<>();
        Direction currentDirection = Direction.UP;
        while (true) {
            visited.add(new Pair(currentPosition, currentDirection));
            if (isOutOfBounds(currentPosition, currentDirection, matrix)) {
                return false;
            }
            while (matrix[currentPosition.move(currentDirection).row][currentPosition.move(currentDirection).col]
                    .equals("#")) {
                currentDirection = currentDirection.rotate();
            }
            currentPosition = currentPosition.move(currentDirection);
            if (visited.contains(new Pair(currentPosition, currentDirection))) {
                return true;
            }
        }
    }

    private record Pair(Position position, Direction direction) {}

    private record Position(int row, int col) {

        private Position move(Direction direction) {
            return switch (direction) {
                case UP -> new Position(row - 1, col);
                case RIGHT -> new Position(row, col + 1);
                case DOWN -> new Position(row + 1, col);
                case LEFT -> new Position(row, col - 1);
            };
        }
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        private Direction rotate() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }
}
