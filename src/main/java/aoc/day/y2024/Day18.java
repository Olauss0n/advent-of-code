package aoc.day.y2024;

import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.Orientation;
import aoc.util.Matrix;
import aoc.util.SearchUtil;
import aoc.util.grid.Position;

public class Day18 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int size = isExample ? 7 : 71;
        Position startPosition = new Position(0, 0);
        Position endPosition = isExample ? new Position(6, 6) : new Position(70, 70);
        Matrix<String> matrix = Matrix.createStringMatrix(size, size).fill(".");
        int i = 0;
        for (String line : inputList) {
            if ((i >= 12 && isExample) || i >= 1024) {
                break;
            }
            Position position = new Position(line.split(","));
            matrix.set(position, "#");
            i++;
        }
        return SearchUtil.dijkstra(
                        new Orientation(startPosition, Direction.RIGHT),
                        state -> state.position().equals(endPosition),
                        current -> matrix.getGridEdges(current, 0))
                .distance();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int size = isExample ? 7 : 71;
        Position startPosition = new Position(0, 0);
        Position endPosition = isExample ? new Position(6, 6) : new Position(70, 70);
        Matrix<String> matrix = Matrix.createStringMatrix(size, size).fill(".");
        Position position = startPosition;
        for (String line : inputList) {
            position = new Position(line.split(","));
            try {
                matrix.set(position, "#");
                SearchUtil.dijkstra(
                        new Orientation(startPosition, Direction.RIGHT),
                        state -> state.position().equals(endPosition),
                        current -> matrix.getGridEdges(current, 0));
            } catch (Exception e) {
                break;
            }
        }
        return position.toString();
    }
}
