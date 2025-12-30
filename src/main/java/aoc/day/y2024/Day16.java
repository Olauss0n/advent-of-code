package aoc.day.y2024;

import aoc.day.AdventOfCodeSolver;
import aoc.util.exceptions.NotImplementedException;
import aoc.util.grid.Direction;
import aoc.util.grid.Matrix;
import aoc.util.grid.Orientation;
import aoc.util.grid.Position;
import aoc.util.parse.Parser;
import aoc.util.path.SearchUtil;

public class Day16 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Parser.toStringMatrix(input);
        Position start = matrix.findPosition("S");
        Position end = matrix.findPosition("E");
        return SearchUtil.dijkstra(
                        new Orientation(start, Direction.RIGHT),
                        state -> state.position().equals(end),
                        current -> SearchUtil.getGridEdges(matrix, current, 1000))
                .distance();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
