package aoc.day.y2024;

import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day07 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return commonPart(input, false);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return commonPart(input, true);
    }

    private Object commonPart(String input, boolean isPartTwo) {
        List<String> inputList = Converter.convertInputToList(input);

        long total = 0;
        for (String line : inputList) {
            String[] split = line.split(": ");
            long target = Long.parseLong(split[0]);
            List<Long> numbers =
                    Arrays.stream(split[1].split(" ")).map(Long::parseLong).toList();
            if (canSolve(target, numbers, isPartTwo)) {
                total += target;
            }
        }
        return total;
    }

    private boolean canSolve(long target, List<Long> numbers, boolean isPartTwo) {
        List<Long> subList = numbers.subList(0, numbers.size() - 1);
        if (numbers.size() == 1) {
            return target == numbers.getFirst();
        }
        if (target % numbers.getLast() == 0 && canSolve(target / numbers.getLast(), subList, isPartTwo)) {
            return true;
        }
        if (target > numbers.getLast() && canSolve(target - numbers.getLast(), subList, isPartTwo)) {
            return true;
        }
        if (isPartTwo) {
            String tar = String.valueOf(target);
            String last = String.valueOf(numbers.getLast());
            return tar.length() > last.length()
                    && tar.endsWith(last)
                    && canSolve(Long.parseLong(tar.substring(0, tar.length() - last.length())), subList, true);
        }
        return false;
    }
}
