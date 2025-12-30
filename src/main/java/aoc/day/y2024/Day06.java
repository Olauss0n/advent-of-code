package aoc.day.y2024;

import java.util.HashSet;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.Orientation;
import aoc.util.GridUtil.Position;
import aoc.util.Matrix;

public class Day06 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        Position startPosition = matrix.findPosition("^");
        HashSet<Position> visited = findPositions(startPosition, matrix);
        return visited.size();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        Position startPosition = matrix.findPosition("^");
        HashSet<Position> visited = findPositions(startPosition, matrix);

        int loops = 0;
        for (int row = 0; row < matrix.rows(); row++) {
            for (int col = 0; col < matrix.columns(); col++) {
                Position position = new Position(col, row);
                if (!matrix.get(position).equals(".")) {
                    continue;
                }
                if (!visited.contains(new Position(col, row))) {
                    continue;
                }
                if (startPosition.xPos() == col && startPosition.yPos() == row) {
                    continue;
                }
                matrix.set(position, "#");
                if (isLoop(startPosition, matrix)) {
                    loops++;
                }
                matrix.set(position, ".");
            }
        }
        return loops;
    }

    private HashSet<Position> findPositions(Position currentPosition, Matrix<String> matrix) {
        HashSet<Position> visited = new HashSet<>();
        Direction currentDirection = Direction.UP;
        while (true) {
            visited.add(currentPosition);
            if (!matrix.isWithinBounds(currentPosition.move(currentDirection))) {
                break;
            }
            while (matrix.get(currentPosition.move(currentDirection)).equals("#")) {
                currentDirection = currentDirection.rotateClockwise();
            }
            currentPosition = currentPosition.move(currentDirection);
        }
        return visited;
    }

    private boolean isLoop(Position currentPosition, Matrix<String> matrix) {
        HashSet<Orientation> visited = new HashSet<>();
        Direction currentDirection = Direction.UP;
        while (true) {
            visited.add(new Orientation(currentPosition, currentDirection));
            if (!matrix.isWithinBounds(currentPosition.move(currentDirection))) {
                return false;
            }
            while (matrix.get(currentPosition.move(currentDirection)).equals("#")) {
                currentDirection = currentDirection.rotateClockwise();
            }
            currentPosition = currentPosition.move(currentDirection);
            if (visited.contains(new Orientation(currentPosition, currentDirection))) {
                return true;
            }
        }
    }
}
