package aoc.day.y2025;

import java.util.ArrayList;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day02 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input, ",");
        long result = 0;
        for (String line : inputList) {
            String start = line.split("-")[0];
            String end = line.split("-")[1];
            for (long i = Long.parseLong(start); i <= Long.parseLong(end); i++) {
                String value = String.valueOf(i);
                String firstHalf = value.substring(0, value.length() / 2);
                String secondHalf = value.substring(value.length() / 2);
                if (firstHalf.equals(secondHalf)) {
                    long illegalValue = Long.parseLong(firstHalf + secondHalf);
                    result += illegalValue;
                }
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input, ",");
        long result = 0;
        for (String line : inputList) {
            String start = line.split("-")[0];
            String end = line.split("-")[1];
            for (long number = Long.parseLong(start); number <= Long.parseLong(end); number++) {
                String value = String.valueOf(number);
                for (int repeatingRange = 2;
                        repeatingRange <= String.valueOf(number).length();
                        repeatingRange++) {
                    if (value.length() % repeatingRange != 0) {
                        continue;
                    }
                    if (sliceIntoParts(value, repeatingRange).stream()
                                    .map(Long::parseLong)
                                    .distinct()
                                    .count()
                            == 1) {
                        result += number;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static List<String> sliceIntoParts(String string, int parts) {
        List<String> result = new ArrayList<>();
        int partSize = string.length() / parts;
        for (int i = 0; i < string.length(); i += partSize) {
            result.add(string.substring(i, i + partSize));
        }
        return result;
    }
}
