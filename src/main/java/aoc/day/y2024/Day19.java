package aoc.day.y2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day19 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return commonPart(input).count();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return commonPart(input).reduce(Long::sum).orElseThrow();
    }

    private final HashMap<String, Long> cache = new HashMap<>();

    private Stream<Long> commonPart(String input) {
        List<String> inputList = Converter.convertInputToList(input).stream()
                .filter(element -> !element.isBlank())
                .toList();
        List<String> towelPatterns =
                Arrays.stream(inputList.getFirst().split(", ")).toList();
        List<String> designs = inputList.subList(1, inputList.size());

        for (String design : designs) {
            countPossible(design, towelPatterns);
        }
        return designs.stream().map(cache::get).filter(value -> value > 0);
    }

    private long countPossible(String design, List<String> patterns) {
        if (cache.containsKey(design)) {
            return cache.get(design);
        }
        long count = 0;
        for (String pattern : patterns) {
            if (design.equals(pattern)) {
                count += 1;
            } else if (design.startsWith(pattern)) {
                count += countPossible(design.substring(pattern.length()), patterns);
            }
        }
        cache.put(design, count);
        return count;
    }
}
