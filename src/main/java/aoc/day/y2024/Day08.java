package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Reader;

public class Day08 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);

        HashMap<String, List<Position>> antennas = getAntennas(matrix);
        HashSet<Position> antinodes = new HashSet<>();
        for (List<Position> positions : antennas.values()) {
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    Position first = positions.get(i);
                    Position second = positions.get(j);
                    Position firstAntinode = new Position(2 * first.col - second.col, 2 * first.row - second.row);
                    Position secondAntinode = new Position(2 * second.col - first.col, 2 * second.row - first.row);

                    if (isWithinBound(matrix, firstAntinode)) {
                        antinodes.add(firstAntinode);
                    }
                    if (isWithinBound(matrix, secondAntinode)) {
                        antinodes.add(secondAntinode);
                    }
                }
            }
        }
        return antinodes.size();
    }

    @Override
    public Object solvePartTwo() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);

        HashMap<String, List<Position>> antennas = getAntennas(matrix);
        HashSet<Position> antinodes = new HashSet<>();
        for (List<Position> positions : antennas.values()) {
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    Position first = positions.get(i);
                    Position second = positions.get(j);
                    int rowDelta = second.row - first.row;
                    int colDelta = second.col - first.col;

                    while (isWithinBound(matrix, first)) {
                        antinodes.add(first);
                        first = first.move(rowDelta, colDelta);
                    }
                    while (isWithinBound(matrix, second)) {
                        antinodes.add(second);
                        second = second.move(-rowDelta, -colDelta);
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

    private boolean isWithinBound(String[][] matrix, Position pos) {
        return (pos.col >= 0 && pos.row >= 0 && pos.row < matrix.length && pos.col < matrix[0].length);
    }

    private record Position(int row, int col) implements Comparable<Position> {

        private Position move(int row, int col) {
            return new Position(this.row + row, this.col + col);
        }

        @Override
        public int compareTo(Position o) {
            if (this.row == o.row) {
                return this.col - o.col;
            }
            return (this.row - o.row);
        }
    }
}
