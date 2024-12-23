package aoc.day.y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.util.AdventOfCodeSolver;

public class Day13 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return commonPart(input, false);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return commonPart(input, true);
    }

    private Object commonPart(String input, boolean isPartTwo) {
        List<String> inputList = Arrays.stream(input.split("\n\n")).toList();
        String regex = "X[+=](\\d+)|Y[+=](\\d+)";
        Pattern pattern = Pattern.compile(regex);
        long tokens = 0;
        for (String clawMachine : inputList) {
            List<String> machine = new ArrayList<>();
            Matcher matcher = pattern.matcher(clawMachine);
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    machine.add(matcher.group(1));
                }
                if (matcher.group(2) != null) {
                    machine.add(matcher.group(2));
                }
            }
            List<Double> xy = performCramersRule(machine, isPartTwo);
            double x = xy.getFirst();
            double y = xy.getLast();

            if ((long) x == x && (long) y == y) {
                if ((x < 100 && y < 100) || isPartTwo) {
                    tokens += (long) x * 3 + (long) y;
                }
            }
        }
        return tokens;
    }

    private List<Double> performCramersRule(List<String> clawMachine, boolean isPartTwo) {
        long a1 = Integer.parseInt(clawMachine.get(0));
        long a2 = Integer.parseInt(clawMachine.get(1));
        long b1 = Integer.parseInt(clawMachine.get(2));
        long b2 = Integer.parseInt(clawMachine.get(3));
        long c1 = Integer.parseInt(clawMachine.get(4));
        long c2 = Integer.parseInt(clawMachine.get(5));

        if (isPartTwo) {
            c1 += 10000000000000L;
            c2 += 10000000000000L;
        }

        // Calculate the denominator (determinant of coefficient matrix)
        long denominator = a1 * b2 - a2 * b1;

        // Calculate x and y directly
        double x = (double) (c1 * b2 - c2 * b1) / denominator;
        double y = (double) (a1 * c2 - a2 * c1) / denominator;

        return List.of(x, y);
    }
}
