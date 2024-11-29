package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day02 implements AdventOfCodeSolver {

    private static final Integer MAX_RED_CUBES = 12;
    private static final Integer MAX_GREEN_CUBES = 13;
    private static final Integer MAX_BLUE_CUBES = 14;

    @Override
    public Object solvePartOne() {
        List<String> inputList = Reader.readInputAsList(this.getClass());

        List<String> approvedGameIds = new ArrayList<>();

        for (String game : inputList) {
            List<String> gameIdAndCubes = Arrays.asList(game.split(":"));
            String gameId = gameIdAndCubes.get(0).replaceAll("Game ", "");
            List<String> sets = Arrays.asList(gameIdAndCubes.get(1).split(";"));

            boolean shouldAddGameId = true;

            for (String set : sets) {
                List<String> cubes = Arrays.asList(set.split(","));

                for (String cube : cubes) {

                    List<String> cubeList = Arrays.asList(cube.strip().split(" "));
                    Integer amount = Integer.parseInt(cubeList.get(0));
                    String color = cubeList.get(1);

                    switch (color) {
                        case "red":
                            if (amount > MAX_RED_CUBES) {
                                shouldAddGameId = false;
                            }
                        case "green":
                            if (amount > MAX_GREEN_CUBES) {
                                shouldAddGameId = false;
                            }
                        case "blue":
                            if (amount > MAX_BLUE_CUBES) {
                                shouldAddGameId = false;
                            }
                    }
                }
            }
            if (shouldAddGameId) {
                approvedGameIds.add(gameId);
            }
        }
        return approvedGameIds.stream()
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    @Override
    public Object solvePartTwo() {
        List<String> inputList = Reader.readInputAsList(this.getClass());

        List<Integer> minimumSetList = new ArrayList<>();

        for (String game : inputList) {
            Integer redMaxAmount = 0;
            Integer greenMaxAmount = 0;
            Integer blueMaxAmount = 0;
            List<String> gameIdAndCubes = Arrays.asList(game.split(":"));
            String gameId = gameIdAndCubes.get(0).replaceAll("Game ", "");
            List<String> sets = Arrays.asList(gameIdAndCubes.get(1).split(";"));

            for (String set : sets) {
                List<String> cubes = Arrays.asList(set.split(","));

                for (String cube : cubes) {

                    List<String> cubeList = Arrays.asList(cube.strip().split(" "));
                    Integer amount = Integer.parseInt(cubeList.get(0));
                    String color = cubeList.get(1);

                    switch (color) {
                        case "red":
                            if (amount > redMaxAmount) {
                                redMaxAmount = amount;
                            }
                            continue;
                        case "green":
                            if (amount > greenMaxAmount) {
                                greenMaxAmount = amount;
                            }
                            continue;
                        case "blue":
                            if (amount > blueMaxAmount) {
                                blueMaxAmount = amount;
                            }
                    }
                }
            }
            Integer minimumPowerNeeded = redMaxAmount * greenMaxAmount * blueMaxAmount;
            minimumSetList.add(minimumPowerNeeded);
        }
        return minimumSetList.stream().reduce(Integer::sum).orElseThrow();
    }
}
