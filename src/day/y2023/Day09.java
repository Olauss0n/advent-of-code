package day.y2023;

import util.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day09 {

    public static void run() {
        System.out.print("For part one: ");
        runPartOne();
        System.out.print("For part two: ");
        runPartTwo();
    }

    private static void runPartOne() {
        List<List<Long>> input = Reader.readInputAsList("y2023", "09").stream()
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
        extrapolatedValues.stream().reduce(Long::sum).ifPresent(System.out::println);
    }

    private static void runPartTwo() {
        List<List<Long>> input = Reader.readInputAsList("y2023", "09").stream()
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
        extrapolatedValues.stream().reduce(Long::sum).ifPresent(System.out::println);
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
