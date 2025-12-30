package aoc.day.y2025;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aoc.day.AdventOfCodeSolver;
import aoc.util.parse.Parser;

public class Day03 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Parser.toList(input);
        long result = 0;
        for (String line : inputList) {
            int digitsToPick = 2;
            result += findHighestDigits(line, digitsToPick);
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Parser.toList(input);
        long result = 0;
        for (String line : inputList) {
            int digitsToPick = 12;
            result += findHighestDigits(line, digitsToPick);
        }
        return result;
    }

    private Long findHighestDigits(String line, int digitsToPick) {
        List<Long> digits = new ArrayList<>();
        int startIndex = 0;
        for (int remaining = digitsToPick - 1; remaining >= 0; remaining--) {
            Joltage joltage = findHighestDigit(line, startIndex, remaining);
            digits.add(joltage.value());
            startIndex = joltage.index();
        }
        return Long.parseLong(digits.stream().map(String::valueOf).collect(Collectors.joining()));
    }

    private Joltage findHighestDigit(String line, int startIndex, int endOffset) {
        long value = -1;
        int index = -1;
        for (int i = startIndex; i < line.length() - endOffset; i++) {
            long currentDigit = Long.parseLong(line.substring(i, i + 1));
            if (currentDigit > value) {
                value = currentDigit;
                index = i;
            }
        }
        return new Joltage(value, index + 1);
    }

    private record Joltage(long value, int index) {}
}
