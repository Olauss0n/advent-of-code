package aoc.day.y2025;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.grid.Direction;
import aoc.util.grid.Matrix;
import aoc.util.grid.Position;

public class Day07 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);
        Position current = matrix.findPosition("S");
        HashSet<Position> hits = new HashSet<>();
        traceBeams(matrix, current, hits);
        return hits.size();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);
        Position current = matrix.findPosition("S");
        traceBeams(matrix, current, new HashSet<>());
        HashMap<Integer, Long> timelines = new HashMap<>();
        for (int i = 0; i < matrix.columns(); i++) {
            if (i == current.xPos()) {
                timelines.put(i, 1L);
            } else {
                timelines.put(i, 0L);
            }
        }
        for (int i = 0; i < matrix.columns(); i = i + 2) {
            for (int j = 1; j < matrix.rows() - 1; j++) {
                Position position = new Position(j, i);
                if (matrix.get(position).equals("^")
                        && matrix.get(position.move(Direction.UP)).equals("|")) {
                    Long currentTimelines = timelines.get(j);
                    Long leftTimelines = timelines.get(j - 1);
                    Long rightTimelines = timelines.get(j + 1);
                    timelines.put(j, 0L);
                    timelines.put(j - 1, leftTimelines + currentTimelines);
                    timelines.put(j + 1, rightTimelines + currentTimelines);
                }
            }
        }
        return timelines.values().stream().mapToLong(Long::longValue).sum();
    }

    private void traceBeams(Matrix<String> matrix, Position position, HashSet<Position> hits) {
        List<Direction> reflections = List.of(Direction.LEFT, Direction.RIGHT);
        if (matrix.get(position).equals(".")) {
            matrix.set(position, "|");
        }
        position = position.move(Direction.DOWN);
        if (!matrix.isWithinBounds(position)) {
            return;
        }
        if (matrix.get(position).equals(".")) {
            matrix.set(position, "|");
            traceBeams(matrix, position, hits);
        }
        if (matrix.get(position).equals("^")) {
            for (Direction direction : reflections) {
                hits.add(position);
                traceBeams(matrix, position.move(direction), hits);
            }
        }
    }
}
