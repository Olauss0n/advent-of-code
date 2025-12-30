package aoc.day.y2024;

import aoc.day.AdventOfCodeSolver;
import aoc.util.exception.NotImplementedException;
import aoc.util.grid.Direction;
import aoc.util.grid.Matrix;
import aoc.util.grid.OctagonalDirection;
import aoc.util.grid.Position;
import aoc.util.parse.Parser;

public class Day20 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Parser.toStringMatrix(input);
        Position currentPosition = matrix.findPosition("S");
        Position endPosition = matrix.findPosition("E");
        Matrix<Integer> distances = Matrix.createIntMatrix(matrix).fill(-1);
        distances.set(currentPosition, 0);

        while (!currentPosition.equals(endPosition)) {
            for (Direction direction : Direction.values()) {
                Position nextPosition = currentPosition.move(direction);
                if (!matrix.isWithinBounds(nextPosition)) {
                    continue;
                }
                if (matrix.get(nextPosition).equals("#")) {
                    continue;
                }
                if (distances.get(nextPosition) != -1) {
                    continue;
                }
                distances.set(nextPosition, distances.get(currentPosition) + 1);
                currentPosition = nextPosition;
            }
        }
        int result = 0;
        for (int y = 0; y < matrix.rows(); y++) {
            for (int x = 0; x < matrix.columns(); x++) {
                if (matrix.get(new Position(x, y)).equals("#")) {
                    continue;
                }
                for (OctagonalDirection direction : OctagonalDirection.values()) {
                    Position newPosition = new Position(x, y).move2Steps(direction);
                    if (!matrix.isWithinBounds(newPosition)) {
                        continue;
                    }
                    if (matrix.get(newPosition).equals("#")) {
                        continue;
                    }
                    if (distances.get(new Position(x, y)) - distances.get(newPosition) >= (isExample ? 4 : 102)) {
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
