package aoc.day.y2024;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil;
import aoc.util.MatrixUtil.Direction;
import aoc.util.MatrixUtil.Position;
import aoc.util.MatrixUtil.State;
import aoc.util.exceptions.NotImplementedException;

public class Day16 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);
        Position start = MatrixUtil.findPosition(matrix, "S");
        Position end = MatrixUtil.findPosition(matrix, "E");
        return MatrixUtil.calculateDistance(matrix, new State(start, Direction.RIGHT, 0), end, 1000);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
