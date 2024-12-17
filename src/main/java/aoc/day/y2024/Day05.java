package aoc.day.y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day05 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input) {
        return commonPart(input, true);
    }

    @Override
    public Object solvePartTwo(String input) {
        return commonPart(input, false);
    }

    private int commonPart(String input, boolean isPartOne) {
        List<String> inputList = Converter.convertInputToList(input);
        HashMap<Pair, Integer> cache = new HashMap<>();
        List<List<Integer>> updates = new ArrayList<>();
        for (String line : inputList) {
            if (line.contains("|")) {
                List<Integer> pages =
                        Arrays.stream(line.split("\\|")).map(Integer::parseInt).toList();
                cache.put(new Pair(pages.getFirst(), pages.getLast()), -1);
                cache.put(new Pair(pages.getLast(), pages.getFirst()), 1);
            }
            if (line.contains(",")) {
                List<Integer> update =
                        Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
                updates.add(update);
            }
        }
        int totalPartOne = 0;
        int totalPartTwo = 0;
        for (List<Integer> update : updates) {
            if (isOrdered(update, cache)) {
                totalPartOne += update.get(Math.floorDiv(update.size(), 2));
            } else {
                List<Integer> sortableList = new ArrayList<>(update);
                sortableList.sort((a, b) ->
                        Integer.compare(cache.getOrDefault(new Pair(a, b), 0), cache.getOrDefault(new Pair(b, a), 0)));
                totalPartTwo += sortableList.get(Math.floorDiv(sortableList.size(), 2));
            }
        }
        return isPartOne ? totalPartOne : totalPartTwo;
    }

    private boolean isOrdered(List<Integer> update, HashMap<Pair, Integer> cache) {
        for (int i = 0; i < update.size(); i++) {
            for (int j = i + 1; j < update.size(); j++) {
                Pair key = new Pair(update.get(i), update.get(j));
                if (cache.containsKey(key) && cache.get(key) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private record Pair(int left, int right) {}
}
