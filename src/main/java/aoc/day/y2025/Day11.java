package aoc.day.y2025;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day11 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        Map<String, List<String>> map = new HashMap<>();
        for (String line : inputList) {
            String parent = line.split(": ")[0];
            List<String> children = Arrays.asList(line.split(": ")[1].split(" "));
            map.put(parent, children);
        }
        return calculatePaths(map, "you", "out", new HashMap<>());
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        Map<String, List<String>> map = new HashMap<>();
        for (String line : inputList) {
            String parent = line.split(": ")[0];
            List<String> children = Arrays.asList(line.split(": ")[1].split(" "));
            map.put(parent, children);
        }
        long first_a = calculatePaths(map, "svr", "dac", new HashMap<>());
        long first_b = calculatePaths(map, "dac", "fft", new HashMap<>());
        long first_c = calculatePaths(map, "fft", "out", new HashMap<>());
        long second_a = calculatePaths(map, "svr", "fft", new HashMap<>());
        long second_b = calculatePaths(map, "fft", "dac", new HashMap<>());
        long second_c = calculatePaths(map, "dac", "out", new HashMap<>());

        return first_a * first_b * first_c + second_a * second_b * second_c;
    }

    private long calculatePaths(Map<String, List<String>> map, String start, String end, HashMap<String, Long> cache) {
        if (cache.containsKey(start)) {
            return cache.get(start);
        }
        if (start.equals(end)) {
            return 1L;
        }
        long paths = 0;
        for (String child : Optional.ofNullable(map.get(start)).orElse(List.of())) {
            paths += calculatePaths(map, child, end, cache);
        }
        cache.put(start, paths);
        return paths;
    }
}
