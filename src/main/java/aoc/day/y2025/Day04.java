package aoc.day.y2025;

import java.util.ArrayList;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.grid.Matrix;
import aoc.util.grid.OctagonalDirection;
import aoc.util.grid.Position;
import aoc.util.parse.Parser;

public class Day04 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Parser.toStringMatrix(input);
        int result = 0;
        for (int row = 0; row < matrix.rows(); row++) {
            for (int column = 0; column < matrix.columns(); column++) {
                int occurrences = 0;
                Position currentPosition = new Position(column, row);
                if (matrix.get(currentPosition).equals("@")) {
                    for (OctagonalDirection direction : OctagonalDirection.values()) {
                        Position newPosition = currentPosition.move1Step(direction);
                        if (matrix.isWithinBounds(newPosition)) {
                            if (matrix.get(newPosition).equals("@")) {
                                occurrences += 1;
                            }
                        }
                    }
                    if (occurrences < 4) {
                        result += 1;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        Matrix<String> matrix = Parser.toStringMatrix(input);
        int result = 0;
        int removals;
        do {
            List<Position> positions = new ArrayList<>();
            for (int row = 0; row < matrix.rows(); row++) {
                for (int column = 0; column < matrix.columns(); column++) {
                    int occurrences = 0;
                    Position currentPosition = new Position(column, row);
                    if (matrix.get(currentPosition).equals("@")) {
                        for (OctagonalDirection direction : OctagonalDirection.values()) {
                            Position newPosition = currentPosition.move1Step(direction);
                            if (matrix.isWithinBounds(newPosition)) {
                                if (matrix.get(newPosition).equals("@")) {
                                    occurrences += 1;
                                }
                            }
                        }
                        if (occurrences < 4) {
                            positions.add(currentPosition);
                        }
                    }
                }
            }
            for (Position position : positions) {
                matrix.set(position, ".");
            }
            removals = positions.size();
            result += removals;
        } while (removals != 0);
        return result;
    }
}
