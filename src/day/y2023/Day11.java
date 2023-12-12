package day.y2023;

import util.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day11 {

    public static void run() {
        System.out.println("For part one: ");
        runCommonPart(2);
        System.out.println("For part two: ");
        runCommonPart(1000000);
    }

    private static void runCommonPart(long expandMultiplier) {
        expandMultiplier--;
        List<List<String>> input = Reader.readFileAsArrayList("y2023", "11").stream()
                .map(line -> line.split(""))
                .map(Arrays::asList)
                .toList();

        ArrayList<Galaxy> galaxies = new ArrayList<>();
        ArrayList<Integer> emptyRows = new ArrayList<>();
        ArrayList<Integer> emptyColumns = new ArrayList<>();
        HashMap<Integer, ArrayList<Galaxy>> columnToGalaxy = new HashMap<>();

        for (int row = 0; row < input.size(); row++) {
            boolean isEmptyRow = true;
            for (int column = 0; column < input.get(row).size(); column++) {
                String element = input.get(row).get(column);
                if (element.equals("#")) {
                    isEmptyRow = false;
                    Galaxy galaxy = new Galaxy(row, column);
                    galaxies.add(galaxy);
                    columnToGalaxy.computeIfAbsent(column, x -> new ArrayList<>()).add(galaxy);
                }
            }
            if (isEmptyRow) {
                emptyRows.add(row);
            }
        }

        for (int column = 0; column < input.get(0).size(); column++) {
            if (columnToGalaxy.get(column) == null) {
                emptyColumns.add(column);
            }
        }

        long totalDistance = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                long distance = calculateDistance(galaxies.get(i), galaxies.get(j));
                long emptyRowDistance = (Math.abs(Collections.binarySearch(emptyRows, galaxies.get(i).row) - Collections.binarySearch(emptyRows, galaxies.get(j).row))) * expandMultiplier;
                long emptyColumnDistance = (Math.abs(Collections.binarySearch(emptyColumns, galaxies.get(i).column) - Collections.binarySearch(emptyColumns, galaxies.get(j).column))) * expandMultiplier;
                //System.out.println("Distance between galaxy " + (i + 1) + ": " + galaxies.get(i)+ " and galaxy " + (j+1) + ": " + galaxies.get(j) + " is: " +  distance + " + " + emptyRowDistance + " + " + emptyColumnDistance);
                totalDistance += (distance + emptyRowDistance + emptyColumnDistance);
            }
        }
        System.out.println(totalDistance);
    }

    private static long calculateDistance(Galaxy galaxy1, Galaxy galaxy2) {
        return Math.abs(galaxy1.row - galaxy2.row)
                + Math.abs(galaxy1.column - galaxy2.column);
    }

    private record Galaxy(int row, int column) {}
}
