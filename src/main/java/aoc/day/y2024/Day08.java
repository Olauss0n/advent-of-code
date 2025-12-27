package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil.Position;

import static aoc.util.GridUtil.isWithinBounds;

public class Day08 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);

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

                    if (isWithinBounds(matrix, firstAntinode)) {
                        antinodes.add(firstAntinode);
                    }
                    if (isWithinBounds(matrix, secondAntinode)) {
                        antinodes.add(secondAntinode);
                    }
                }
            }
        }
        return antinodes.size();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);

        HashMap<String, List<Position>> antennas = getAntennas(matrix);
        HashSet<Position> antinodes = new HashSet<>();
        for (List<Position> positions : antennas.values()) {
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    Position first = positions.get(i);
                    Position second = positions.get(j);
                    int xDelta = second.xPos() - first.xPos();
                    int yDelta = second.yPos() - first.yPos();

                    while (isWithinBounds(matrix, first)) {
                        antinodes.add(first);
                        first = first.move(xDelta, yDelta);
                    }
                    while (isWithinBounds(matrix, second)) {
                        antinodes.add(second);
                        second = second.move(-xDelta, -yDelta);
                    }
                }
            }
        }
        return antinodes.size();
    }

    private static HashMap<String, List<Position>> getAntennas(String[][] matrix) {
        HashMap<String, List<Position>> antennas = new HashMap<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!matrix[row][col].equals(".")) {
                    if (!antennas.containsKey(matrix[row][col])) {
                        antennas.put(matrix[row][col], new ArrayList<>());
                    }
                    antennas.get(matrix[row][col]).add(new Position(row, col));
                }
            }
        }
        return antennas;
    }
}
