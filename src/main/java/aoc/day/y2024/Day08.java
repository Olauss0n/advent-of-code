package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Matrix;
import aoc.util.grid.Position;

public class Day08 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        HashMap<String, List<Position>> antennas = getAntennas(matrix);
        HashSet<Position> antinodes = new HashSet<>();
        for (List<Position> positions : antennas.values()) {
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    Position first = positions.get(i);
                    Position second = positions.get(j);
                    Position firstAntinode =
                            new Position(2 * first.xPos() - second.xPos(), 2 * first.yPos() - second.yPos());
                    Position secondAntinode =
                            new Position(2 * second.xPos() - first.xPos(), 2 * second.yPos() - first.yPos());

                    if (matrix.isWithinBounds(firstAntinode)) {
                        antinodes.add(firstAntinode);
                    }
                    if (matrix.isWithinBounds(secondAntinode)) {
                        antinodes.add(secondAntinode);
                    }
                }
            }
        }
        return antinodes.size();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        HashMap<String, List<Position>> antennas = getAntennas(matrix);
        HashSet<Position> antinodes = new HashSet<>();
        for (List<Position> positions : antennas.values()) {
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    Position first = positions.get(i);
                    Position second = positions.get(j);
                    int xDelta = second.xPos() - first.xPos();
                    int yDelta = second.yPos() - first.yPos();

                    while (matrix.isWithinBounds(first)) {
                        antinodes.add(first);
                        first = first.move(xDelta, yDelta);
                    }
                    while (matrix.isWithinBounds(second)) {
                        antinodes.add(second);
                        second = second.move(-xDelta, -yDelta);
                    }
                }
            }
        }
        return antinodes.size();
    }

    private static HashMap<String, List<Position>> getAntennas(Matrix<String> matrix) {
        HashMap<String, List<Position>> antennas = new HashMap<>();
        for (int row = 0; row < matrix.rows(); row++) {
            for (int col = 0; col < matrix.columns(); col++) {
                Position position = new Position(col, row);
                if (!matrix.get(position).equals(".")) {
                    if (!antennas.containsKey(matrix.get(position))) {
                        antennas.put(matrix.get(position), new ArrayList<>());
                    }
                    antennas.get(matrix.get(position)).add(new Position(row, col));
                }
            }
        }
        return antennas;
    }
}
