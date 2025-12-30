package aoc.day.y2024;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.Orientation;
import aoc.util.Matrix;
import aoc.util.SearchUtil;
import aoc.util.exceptions.NotImplementedException;
import aoc.util.grid.Position;

public class Day16 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);
        Position start = matrix.findPosition("S");
        Position end = matrix.findPosition("E");
        return SearchUtil.dijkstra(
                        new Orientation(start, Direction.RIGHT),
                        state -> state.position().equals(end),
                        current -> matrix.getGridEdges(current, 1000))
                .distance();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
