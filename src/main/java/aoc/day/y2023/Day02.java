package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day02 implements AdventOfCodeSolver {

    private static final Integer MAX_RED_CUBES = 12;
    private static final Integer MAX_GREEN_CUBES = 13;
    private static final Integer MAX_BLUE_CUBES = 14;

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);

        List<String> approvedGameIds = new ArrayList<>();

        for (String game : inputList) {
            List<String> gameIdAndCubes = Arrays.asList(game.split(":"));
            String gameId = gameIdAndCubes.getFirst().replaceAll("Game ", "");
            String[] sets = gameIdAndCubes.get(1).split(";");

            boolean shouldAddGameId = true;

            for (String set : sets) {
                String[] cubes = set.split(",");

                for (String cube : cubes) {

                    List<String> cubeList = Arrays.asList(cube.strip().split(" "));
                    int amount = Integer.parseInt(cubeList.getFirst());
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
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);

        List<Integer> minimumSetList = new ArrayList<>();

        for (String game : inputList) {
            int redMaxAmount = 0;
            int greenMaxAmount = 0;
            int blueMaxAmount = 0;
            List<String> gameIdAndCubes = Arrays.asList(game.split(":"));
            String gameId = gameIdAndCubes.getFirst().replaceAll("Game ", "");
            String[] sets = gameIdAndCubes.get(1).split(";");

            for (String set : sets) {
                String[] cubes = set.split(",");

                for (String cube : cubes) {

                    List<String> cubeList = Arrays.asList(cube.strip().split(" "));
                    int amount = Integer.parseInt(cubeList.getFirst());
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
