package aoc.day.y2024;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil;
import aoc.util.MatrixUtil.Direction;
import aoc.util.MatrixUtil.OctagonalDirection;
import aoc.util.MatrixUtil.Position;
import aoc.util.exceptions.NotImplementedException;

public class Day20 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);
        Position currentPosition = MatrixUtil.findPosition(matrix, "S");
        Position endPosition = MatrixUtil.findPosition(matrix, "E");
        Integer[][] distances = MatrixUtil.createIntMatrix(matrix[0].length, matrix.length);
        MatrixUtil.fillMatrix(distances, -1);
        distances[currentPosition.yPos()][currentPosition.xPos()] = 0;

        while (!currentPosition.equals(endPosition)) {
            for (Direction direction : MatrixUtil.Direction.values()) {
                Position nextPosition = currentPosition.move(direction);
                if (!MatrixUtil.isWithinBounds(matrix, nextPosition)) {
                    continue;
                }
                if (matrix[nextPosition.yPos()][nextPosition.xPos()].equals("#")) {
                    continue;
                }
                if (distances[nextPosition.yPos()][nextPosition.xPos()] != -1) {
                    continue;
                }
                distances[nextPosition.yPos()][nextPosition.xPos()] =
                        distances[currentPosition.yPos()][currentPosition.xPos()] + 1;
                currentPosition = nextPosition;
            }
        }
        int result = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x].equals("#")) {
                    continue;
                }
                for (OctagonalDirection direction : MatrixUtil.OctagonalDirection.values()) {
                    Position newPosition = new Position(x, y).move2Steps(direction);
                    if (!MatrixUtil.isWithinBounds(matrix, newPosition)) {
                        continue;
                    }
                    if (matrix[newPosition.yPos()][newPosition.xPos()].equals("#")) {
                        continue;
                    }
                    if (distances[y][x] - distances[newPosition.yPos()][newPosition.xPos()] >= (isExample ? 4 : 102)) {
                        result += 1;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
