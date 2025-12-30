package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.grid.Matrix;
import aoc.util.grid.Position;

public class Day11 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return runCommonPart(input, 2);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return runCommonPart(input, isExample ? 100 : 1000000);
    }

    private long runCommonPart(String input, long expandMultiplier) {
        expandMultiplier--;
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        ArrayList<Position> galaxies = new ArrayList<>();
        ArrayList<Integer> emptyRows = new ArrayList<>();
        ArrayList<Integer> emptyColumns = new ArrayList<>();
        HashMap<Integer, ArrayList<Position>> columnToGalaxy = new HashMap<>();

        for (int row = 0; row < matrix.rows(); row++) {
            boolean isEmptyRow = true;
            for (int column = 0; column < matrix.columns(); column++) {
                String element = matrix.get(column, row);
                if (element.equals("#")) {
                    isEmptyRow = false;
                    Position galaxy = new Position(column, row);
                    galaxies.add(galaxy);
                    columnToGalaxy
                            .computeIfAbsent(column, x -> new ArrayList<>())
                            .add(galaxy);
                }
            }
            if (isEmptyRow) {
                emptyRows.add(row);
            }
        }

        for (int column = 0; column < matrix.columns(); column++) {
            if (columnToGalaxy.get(column) == null) {
                emptyColumns.add(column);
            }
        }

        long totalDistance = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                long distance = galaxies.get(i).manhattanDistance(galaxies.get(j));
                long emptyRowDistance = (Math.abs(Collections.binarySearch(
                                        emptyRows, galaxies.get(i).yPos())
                                - Collections.binarySearch(
                                        emptyRows, galaxies.get(j).yPos())))
                        * expandMultiplier;
                long emptyColumnDistance = (Math.abs(Collections.binarySearch(
                                        emptyColumns, galaxies.get(i).xPos())
                                - Collections.binarySearch(
                                        emptyColumns, galaxies.get(j).xPos())))
                        * expandMultiplier;
                totalDistance += (distance + emptyRowDistance + emptyColumnDistance);
            }
        }
        return totalDistance;
    }
}
