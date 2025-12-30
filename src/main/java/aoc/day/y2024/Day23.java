package aoc.day.y2024;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc.day.AdventOfCodeSolver;
import aoc.util.exceptions.NotImplementedException;
import aoc.util.parse.Parser;

public class Day23 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Parser.toList(input);
        HashMap<String, Set<String>> connections = new HashMap<>();
        for (String line : inputList) {
            String first = line.split("-")[0];
            String second = line.split("-")[1];
            connections.computeIfAbsent(first, skip -> new HashSet<>()).add(second);
            connections.computeIfAbsent(second, skip -> new HashSet<>()).add(first);
        }

        Set<Set<String>> interConnections = new HashSet<>();
        connections.keySet().forEach(first -> connections.get(first).stream()
                .filter(second -> !second.equals(first))
                .forEach(second -> connections.get(second).stream()
                        .filter(third ->
                                !third.equals(first) && connections.get(third).contains(first))
                        .forEach(third -> interConnections.add(Set.of(first, second, third)))));

        List<Set<String>> result = interConnections.stream()
                .filter(sublist -> sublist.stream().anyMatch(elem -> elem.startsWith("t")))
                .toList();
        return result.size();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
