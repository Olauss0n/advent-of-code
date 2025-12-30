package aoc.day.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.day.AdventOfCodeSolver;
import aoc.util.SearchUtil;
import aoc.util.parse.Parser;

public class Day10 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Parser.toList(input);
        int result = 0;
        for (String line : inputList) {
            String indicatorLights = line.split(" ")[0].replaceAll("[\\[\\]]", "");
            List<List<Integer>> buttons = parseButtons(line);
            String startState = ".".repeat(indicatorLights.length());
            result += SearchUtil.bfs(startState, state -> state.equals(indicatorLights), current -> buttons.stream()
                            .map(button -> toggle(current, button))
                            .toList())
                    .distance();
        }
        return result;
    }

    private static List<List<Integer>> parseButtons(String line) {
        return Arrays.stream(line.substring(line.indexOf(']') + 1, line.indexOf('{'))
                        .trim()
                        .split(" "))
                .map(button -> button.replaceAll("[()]", "").split(","))
                .map(parts -> Arrays.stream(parts).map(Integer::parseInt).toList())
                .toList();
    }

    private static List<Integer> parseJoltages(String line) {
        return Arrays.stream(Arrays.asList(line.split(" "))
                        .getLast()
                        .replaceAll("[{}]", "")
                        .split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private String toggle(String state, List<Integer> buttonIndices) {
        char[] lights = state.toCharArray();
        for (int index : buttonIndices) {
            if (lights[index] == '.') {
                lights[index] = '#';
            } else {
                lights[index] = '.';
            }
        }
        return new String(lights);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Parser.toList(input);
        long totalPresses = 0;
        for (String line : inputList) {
            List<Integer> joltages = parseJoltages(line);
            List<List<Integer>> buttons = parseButtons(line);
            List<Combination> validCombos = generatePowerSet(buttons, joltages.size());
            totalPresses += findMinPresses(joltages, validCombos, new HashMap<>());
        }
        return totalPresses;
    }

    private long findMinPresses(
            List<Integer> joltages, List<Combination> combinations, Map<List<Integer>, Long> cache) {
        // Base Case: All joltages reached 0
        if (joltages.stream().allMatch(t -> t == 0)) {
            return 0;
        }
        if (cache.containsKey(joltages)) {
            return cache.get(joltages);
        }
        long minTotal = Long.MAX_VALUE / 4;
        for (Combination combination : combinations) {
            // Check if this combination satisfies the PARITY of the joltages
            if (isParityMatch(joltages, combination.delta)) {
                // Check if we can actually subtract it (no negative results)
                if (canSubtract(joltages, combination.delta)) {
                    List<Integer> halvedTargets = new ArrayList<>();
                    for (int i = 0; i < joltages.size(); i++) {
                        halvedTargets.add((joltages.get(i) - combination.delta[i]) / 2);
                    }
                    // Recurse: Total = (Buttons used now) + 2 * (Buttons used for halved joltages)
                    long result = findMinPresses(halvedTargets, combinations, cache);
                    if (result < Long.MAX_VALUE / 4) {
                        minTotal = Math.min(minTotal, combination.cost + 2 * result);
                    }
                }
            }
        }
        cache.put(joltages, minTotal);
        return minTotal;
    }

    private boolean isParityMatch(List<Integer> targets, int[] delta) {
        for (int i = 0; i < targets.size(); i++) {
            if (targets.get(i) % 2 != delta[i] % 2) {
                return false;
            }
        }
        return true;
    }

    private boolean canSubtract(List<Integer> targets, int[] delta) {
        for (int i = 0; i < targets.size(); i++) {
            if (targets.get(i) < delta[i]) {
                return false;
            }
        }
        return true;
    }

    private List<Combination> generatePowerSet(List<List<Integer>> buttons, int numCounters) {
        List<Combination> results = new ArrayList<>();
        int n = buttons.size();
        // 2^n combinations
        for (int i = 0; i < (1 << n); i++) {
            int[] delta = new int[numCounters];
            int cost = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    for (int counterIdx : buttons.get(j)) {
                        delta[counterIdx]++;
                    }
                    cost++;
                }
            }
            results.add(new Combination(delta, cost));
        }
        return results;
    }

    // A "Combination" is a set of buttons pressed 0 or 1 times
    private record Combination(int[] delta, int cost) {}
}
