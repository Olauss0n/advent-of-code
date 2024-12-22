package aoc.day.y2024;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.exceptions.NotImplementedException;

public class Day22 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input) {
        List<String> inputList = Converter.convertInputToList(input);
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
    public Object solvePartTwo(String input) {
        throw new NotImplementedException();
    }

    private long calculateSecretNumber(long number) {
        number = (number ^ (number * 64)) % 16777216;
        number = (number ^ (number / 32)) % 16777216;
        number = (number ^ (number * 2048)) % 16777216;
        return number;
    }
}
