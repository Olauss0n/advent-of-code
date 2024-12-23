package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day09 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<List<Long>> inputList = Converter.convertInputToList(input).stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).toList())
                .toList();

        ArrayList<Long> extrapolatedValues = new ArrayList<>();
        for (List<Long> digits : inputList) {
            extrapolatedValues.add(digits.getLast());
            while (digits.stream().anyMatch(value -> 0L != value)) {
                digits = getDifferences(digits);
                extrapolatedValues.add(digits.getLast());
            }
        }
        return extrapolatedValues.stream().reduce(Long::sum).orElseThrow();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<List<Long>> inputList = Converter.convertInputToList(input).stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).toList())
                .toList();

        ArrayList<Long> extrapolatedValues = new ArrayList<>();
        for (List<Long> digits : inputList) {
            ArrayList<Long> firstValues = new ArrayList<>();
            firstValues.add(digits.getFirst());
            while (digits.stream().anyMatch(value -> 0L != value)) {
                digits = getDifferences(digits);
                firstValues.add(digits.getFirst());
            }
            extrapolatedValues.add(calculateDifference(firstValues));
        }
        return extrapolatedValues.stream().reduce(Long::sum).orElseThrow();
    }

    private static Long calculateDifference(ArrayList<Long> values) {
        Long difference = 0L;
        for (int i = 0; i < values.size(); i++) {
            Long elem = values.get(i);
            if (i % 2 == 0) {
                difference += elem;
            } else {
                difference -= elem;
            }
        }
        return difference;
    }

    private static List<Long> getDifferences(List<Long> digits) {
        return IntStream.range(0, digits.size() - 1)
                .mapToLong(i -> digits.get(i + 1) - digits.get(i))
                .boxed()
                .toList();
    }
}
