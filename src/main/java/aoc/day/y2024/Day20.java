package aoc.day.y2024;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.OctagonalDirection;
import aoc.util.GridUtil.Position;
import aoc.util.exceptions.NotImplementedException;

public class Day20 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);
        Position currentPosition = GridUtil.findPosition(matrix, "S");
        Position endPosition = GridUtil.findPosition(matrix, "E");
        Integer[][] distances = GridUtil.createIntMatrix(matrix[0].length, matrix.length);
        GridUtil.fillMatrix(distances, -1);
        distances[currentPosition.yPos()][currentPosition.xPos()] = 0;

        while (!currentPosition.equals(endPosition)) {
            for (Direction direction : GridUtil.Direction.values()) {
                Position nextPosition = currentPosition.move(direction);
                if (!GridUtil.isWithinBounds(matrix, nextPosition)) {
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
                for (OctagonalDirection direction : GridUtil.OctagonalDirection.values()) {
                    Position newPosition = new Position(x, y).move2Steps(direction);
                    if (!GridUtil.isWithinBounds(matrix, newPosition)) {
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
