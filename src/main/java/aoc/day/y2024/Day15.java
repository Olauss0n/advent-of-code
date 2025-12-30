package aoc.day.y2024;

import java.util.Arrays;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.Position;
import aoc.util.Matrix;
import aoc.util.exceptions.NotImplementedException;

public class Day15 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Arrays.stream(input.split("\n\n")).toList();
        Matrix<String> matrix = Converter.convertInputToStringMatrix(inputList.getFirst());
        List<String> moves =
                Arrays.stream(inputList.getLast().replace("\n", "").split("")).toList();
        Position position = matrix.findPosition("@");
        for (String move : moves) {
            if (canMoveTo(position, convertMoveToDirection(move), matrix)) {
                matrix = move(matrix, position, convertMoveToDirection(move));
            }
            position = matrix.findPosition("@");
        }
        List<Position> boxes = matrix.findPositions("O");
        long result = 0;
        for (Position box : boxes) {
            result += box.xPos() + 100L * box.yPos();
        }
        return result;
    }

    private Direction convertMoveToDirection(String move) {
        return switch (move) {
            case "^" -> Direction.UP;
            case ">" -> Direction.RIGHT;
            case "<" -> Direction.LEFT;
            default -> Direction.DOWN;
        };
    }

    private boolean canMoveTo(Position position, Direction direction, Matrix<String> matrix) {
        Position newPos = position.move(direction);
        boolean isWithinBounds = matrix.isWithinBounds(newPos);
        if (!isWithinBounds) {
            return false;
        }
        boolean isNewPosAWall = matrix.get(newPos).equals("#");
        if (isNewPosAWall) {
            return false;
        }
        boolean isNewPosADot = matrix.get(newPos).equals(".");
        boolean canMoveToNextPos = canMoveTo(newPos, direction, matrix);
        boolean isNewPosAZero = matrix.get(newPos).equals("O");
        return isNewPosADot || (isNewPosAZero && canMoveToNextPos);
    }

    private Matrix<String> move(Matrix<String> matrix, Position position, Direction direction) {
        Position newPos = position.move(direction);
        Position nextPos = newPos.move(direction);

        if (matrix.get(newPos).equals(".")) {
            matrix.set(newPos, "@");
            matrix.set(position, ".");
        } else if (matrix.get(nextPos).equals("#")) {
            return matrix;
        } else if (matrix.get(newPos).equals("O")) {
            matrix = move(matrix, newPos, direction);
            matrix.set(nextPos, "O");
            matrix.set(newPos, "@");
            matrix.set(position, ".");
        }
        return matrix;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
