package aoc.day.y2024;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day02 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int counter = 0;
        for (String line : inputList) {
            int[] intList = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (increasingOrDecreasing(intList)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int counter = 0;
        for (String line : inputList) {
            int[] intList = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (increasingOrDecreasingWithFaultTolerance(intList)) {
                counter++;
            }
        }
        return counter;
    }

    private boolean increasingOrDecreasing(int[] intList) {
        boolean increasing = IntStream.range(1, intList.length)
                .allMatch(i -> intList[i] > intList[i - 1] && intList[i] - intList[i - 1] <= 3);
        boolean decreasing = IntStream.range(1, intList.length)
                .allMatch(i -> intList[i] < intList[i - 1] && intList[i - 1] - intList[i] <= 3);

        return increasing || decreasing;
    }

    private boolean increasingOrDecreasingWithFaultTolerance(int[] intList) {
        if (increasingOrDecreasing(intList)) {
            return true;
        }

        for (int i = 0; i < intList.length; i++) {
            int index = i;
            int[] reducedList = IntStream.range(0, intList.length)
                    .filter(j -> j != index)
                    .map(j -> intList[j])
                    .toArray();

            if (increasingOrDecreasing(reducedList)) {
                return true;
            }
        }
        return false;
    }
}
