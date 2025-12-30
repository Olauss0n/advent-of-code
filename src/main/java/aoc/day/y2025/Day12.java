package aoc.day.y2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.day.AdventOfCodeSolver;
import aoc.util.exceptions.NoPuzzleAvailableException;

public class Day12 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Arrays.asList(input.split("\n\n"));
        String[] regions = inputList.getLast().split("\n");
        Pattern pattern = Pattern.compile("\\d+");
        int result = 0;
        for (String region : regions) {
            Matcher matcher = pattern.matcher(region);
            List<Integer> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
            int width = numbers.getFirst();
            int length = numbers.get(1);
            int quantity = numbers.subList(2, numbers.size()).stream()
                    .reduce(Integer::sum)
                    .orElseThrow();
            if (width / 3 * length / 3 >= quantity) {
                result += 1;
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NoPuzzleAvailableException();
    }
}
