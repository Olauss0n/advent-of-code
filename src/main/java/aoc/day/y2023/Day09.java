package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day09 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne() {
        List<List<Long>> input = Reader.readInputAsList(this.getClass()).stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).toList())
                .toList();

        ArrayList<Long> extrapolatedValues = new ArrayList<>();
        for (List<Long> digits : input) {
            extrapolatedValues.add(digits.get(digits.size() - 1));
            while (digits.stream().anyMatch(value -> 0L != value)) {
                digits = getDifferences(digits);
                extrapolatedValues.add(digits.get(digits.size() - 1));
            }
        }
        return extrapolatedValues.stream().reduce(Long::sum).orElseThrow();
    }

    @Override
    public Object solvePartTwo() {
        List<List<Long>> input = Reader.readInputAsList(this.getClass()).stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).toList())
                .toList();

        ArrayList<Long> extrapolatedValues = new ArrayList<>();
        for (List<Long> digits : input) {
            ArrayList<Long> firstValues = new ArrayList<>();
            firstValues.add(digits.get(0));
            while (digits.stream().anyMatch(value -> 0L != value)) {
                digits = getDifferences(digits);
                firstValues.add(digits.get(0));
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
