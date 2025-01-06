package aoc.day.y2024;

import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil;
import aoc.util.MatrixUtil.Direction;
import aoc.util.MatrixUtil.Position;
import aoc.util.exceptions.NotImplementedException;

public class Day15 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Arrays.stream(input.split("\n\n")).toList();
        String[][] matrix = Converter.convertInputToStringMatrix(inputList.getFirst());
        List<String> moves =
                Arrays.stream(inputList.getLast().replace("\n", "").split("")).toList();
        Position position = MatrixUtil.findPosition(matrix, "@");
        for (String move : moves) {
            if (canMoveTo(position, convertMoveToDirection(move), matrix)) {
                matrix = move(matrix, position, convertMoveToDirection(move));
            }
            position = MatrixUtil.findPosition(matrix, "@");
        }
        List<Position> boxes = MatrixUtil.findPositions(matrix, "O");
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

    private boolean canMoveTo(Position position, Direction direction, String[][] matrix) {
        Position newPos = position.move(direction);
        boolean isWithinBounds = MatrixUtil.isWithinBounds(matrix, newPos);
        if (!isWithinBounds) {
            return false;
        }
        boolean isNewPosAWall = matrix[newPos.yPos()][newPos.xPos()].equals("#");
        if (isNewPosAWall) {
            return false;
        }
        boolean isNewPosADot = matrix[newPos.yPos()][newPos.xPos()].equals(".");
        boolean canMoveToNextPos = canMoveTo(newPos, direction, matrix);
        boolean isNewPosAZero = matrix[newPos.yPos()][newPos.xPos()].equals("O");
        return isNewPosADot || (isNewPosAZero && canMoveToNextPos);
    }

    private String[][] move(String[][] matrix, Position position, Direction direction) {
        Position newPos = position.move(direction);
        Position nextPos = newPos.move(direction);

        if (matrix[newPos.yPos()][newPos.xPos()].equals(".")) {
            matrix[newPos.yPos()][newPos.xPos()] = "@";
            matrix[position.yPos()][position.xPos()] = ".";
        } else if (matrix[nextPos.yPos()][nextPos.xPos()].equals("#")) {
            return matrix;
        } else if (matrix[newPos.yPos()][newPos.xPos()].equals("O")) {
            matrix = move(matrix, newPos, direction);
            matrix[nextPos.yPos()][nextPos.xPos()] = "O";
            matrix[newPos.yPos()][newPos.xPos()] = "@";
            matrix[position.yPos()][position.xPos()] = ".";
        }
        return matrix;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
