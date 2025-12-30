package aoc.day.y2024;

import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.exceptions.NotImplementedException;
import aoc.util.parse.Parser;

public class Day22 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Parser.toList(input);
        long result = 0;
        for (String line : inputList) {
            long secretNumber = Long.parseLong(line);
            for (int i = 0; i < 2000; i++) {
                secretNumber = calculateSecretNumber(secretNumber);
            }
            result += secretNumber;
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }

    private long calculateSecretNumber(long number) {
        number = (number ^ (number * 64)) % 16777216;
        number = (number ^ (number / 32)) % 16777216;
        number = (number ^ (number * 2048)) % 16777216;
        return number;
    }
}
