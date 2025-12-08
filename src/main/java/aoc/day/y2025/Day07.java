package aoc.day.y2025;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil;
import aoc.util.MatrixUtil.Direction;
import aoc.util.MatrixUtil.Position;

public class Day07 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[][] matrix = Converter.convertListInputToStringMatrix(Converter.convertInputToList(input));
        Position current = MatrixUtil.findPosition(matrix, "S");
        HashSet<Position> hits = new HashSet<>();
        traceBeams(matrix, current, hits);
        return hits.size();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        String[][] matrix = Converter.convertListInputToStringMatrix(Converter.convertInputToList(input));
        Position current = MatrixUtil.findPosition(matrix, "S");
        traceBeams(matrix, current, new HashSet<>());
        HashMap<Integer, Long> timelines = new HashMap<>();
        for (int i = 0; i < matrix[0].length; i++) {
            if (i == current.xPos()) {
                timelines.put(i, 1L);
            } else {
                timelines.put(i, 0L);
            }
        }
        for (int i = 0; i < matrix[0].length; i = i + 2) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if (matrix[i][j].equals("^") && matrix[i - 1][j].equals("|")) {
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

    private void traceBeams(String[][] matrix, Position position, HashSet<Position> hits) {
        List<Direction> reflections = List.of(Direction.LEFT, Direction.RIGHT);
        if (matrix[position.yPos()][position.xPos()].equals(".")) {
            matrix[position.yPos()][position.xPos()] = "|";
        }
        position = position.move(Direction.DOWN);
        if (!MatrixUtil.isWithinBounds(matrix, position)) {
            return;
        }
        if (matrix[position.yPos()][position.xPos()].equals(".")) {
            matrix[position.yPos()][position.xPos()] = "|";
            traceBeams(matrix, position, hits);
        }
        if (matrix[position.yPos()][position.xPos()].equals("^")) {
            for (Direction direction : reflections) {
                hits.add(position);
                traceBeams(matrix, position.move(direction), hits);
            }
        }
    }
}
