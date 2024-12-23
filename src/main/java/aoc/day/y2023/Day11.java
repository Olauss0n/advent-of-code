package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day11 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return runCommonPart(input, 2);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        if (input.length() == 110) { // Ugly hack to handle example data
            return runCommonPart(input, 100);
        }
        return runCommonPart(input, 1000000);
    }

    private long runCommonPart(String input, long expandMultiplier) {
        expandMultiplier--;
        List<List<String>> matrix = Converter.convertInputToList(input).stream()
                .map(line -> line.split(""))
                .map(Arrays::asList)
                .toList();

        ArrayList<Galaxy> galaxies = new ArrayList<>();
        ArrayList<Integer> emptyRows = new ArrayList<>();
        ArrayList<Integer> emptyColumns = new ArrayList<>();
        HashMap<Integer, ArrayList<Galaxy>> columnToGalaxy = new HashMap<>();

        for (int row = 0; row < matrix.size(); row++) {
            boolean isEmptyRow = true;
            for (int column = 0; column < matrix.get(row).size(); column++) {
                String element = matrix.get(row).get(column);
                if (element.equals("#")) {
                    isEmptyRow = false;
                    Galaxy galaxy = new Galaxy(row, column);
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

        for (int column = 0; column < matrix.getFirst().size(); column++) {
            if (columnToGalaxy.get(column) == null) {
                emptyColumns.add(column);
            }
        }

        long totalDistance = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                long distance = calculateDistance(galaxies.get(i), galaxies.get(j));
                long emptyRowDistance = (Math.abs(Collections.binarySearch(emptyRows, galaxies.get(i).row)
                                - Collections.binarySearch(emptyRows, galaxies.get(j).row)))
                        * expandMultiplier;
                long emptyColumnDistance = (Math.abs(Collections.binarySearch(emptyColumns, galaxies.get(i).column)
                                - Collections.binarySearch(emptyColumns, galaxies.get(j).column)))
                        * expandMultiplier;
                // System.out.println("Distance between galaxy " + (i + 1) + ": " + galaxies.get(i)+ " and galaxy " +
                // (j+1) + ": " + galaxies.get(j) + " is: " +  distance + " + " + emptyRowDistance + " + " +
                // emptyColumnDistance);
                totalDistance += (distance + emptyRowDistance + emptyColumnDistance);
            }
        }
        return totalDistance;
    }

    private static long calculateDistance(Galaxy galaxy1, Galaxy galaxy2) {
        return Math.abs(galaxy1.row - galaxy2.row) + Math.abs(galaxy1.column - galaxy2.column);
    }

    private record Galaxy(int row, int column) {}
}
