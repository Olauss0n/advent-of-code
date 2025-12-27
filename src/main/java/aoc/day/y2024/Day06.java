package aoc.day.y2024;

import java.util.HashSet;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.Position;

public class Day06 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);

        Position startPosition = getStartPosition(matrix);
        HashSet<Position> visited = findPositions(startPosition, matrix);
        return visited.size();
    }

    private Position getStartPosition(String[][] matrix) {
        Position startPosition = null;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("^")) {
                    startPosition = new Position(col, row);
                }
            }
        }
        if (startPosition == null) {
            throw new RuntimeException("No start position found");
        }
        return startPosition;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);

        Position startPosition = getStartPosition(matrix);
        HashSet<Position> visited = findPositions(startPosition, matrix);

        int loops = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!matrix[row][col].equals(".")) {
                    continue;
                }
                if (!visited.contains(new Position(col, row))) {
                    continue;
                }
                if (startPosition.xPos() == col && startPosition.yPos() == row) {
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
            if (!GridUtil.isWithinBounds(matrix, currentPosition, currentDirection)) {
                break;
            }
            while (matrix[currentPosition.move(currentDirection).yPos()][
                    currentPosition.move(currentDirection).xPos()].equals("#")) {
                currentDirection = currentDirection.rotateClockwise();
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
            if (!GridUtil.isWithinBounds(matrix, currentPosition, currentDirection)) {
                return false;
            }
            while (matrix[currentPosition.move(currentDirection).yPos()][
                    currentPosition.move(currentDirection).xPos()].equals("#")) {
                currentDirection = currentDirection.rotateClockwise();
            }
            currentPosition = currentPosition.move(currentDirection);
            if (visited.contains(new Pair(currentPosition, currentDirection))) {
                return true;
            }
        }
    }

    private record Pair(Position position, Direction direction) {}
}
