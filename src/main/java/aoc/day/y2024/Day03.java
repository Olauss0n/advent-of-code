package aoc.day.y2024;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day03 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne() {
        String input = Reader.readInputAsSingleString(this.getClass());
        return commonPart(input);
    }

    @Override
    public Object solvePartTwo() {
        String input = Reader.readInputAsSingleString(this.getClass());
        String regexRemoval = "don't\\(\\)(?:(?!do\\(\\)).)*do\\(\\)";
        String filteredInput = input.replaceAll(regexRemoval, "");
        return commonPart(filteredInput);
    }

    private long commonPart(String input) {
        String regex = "mul\\(\\d+,\\d+\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        long result = 0;
        while (matcher.find()) {
            String[] split = matcher.group().split(",");
            long first = Long.parseLong(split[0].replace("mul(", ""));
            long second = Long.parseLong(split[1].replace(")", ""));
            result += first * second;
        }
        return result;
    }
}
