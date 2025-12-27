package aoc.day.y2024;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.Position;
import aoc.util.GridUtil.State;
import aoc.util.exceptions.NotImplementedException;

public class Day16 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);
        Position start = GridUtil.findPosition(matrix, "S");
        Position end = GridUtil.findPosition(matrix, "E");
        return GridUtil.calculateDistance(matrix, new State(start, Direction.RIGHT, 0), end, 1000);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
