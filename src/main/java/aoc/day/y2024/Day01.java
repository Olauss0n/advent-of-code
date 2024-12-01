package aoc.day.y2024;

import java.util.ArrayList;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day01 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();

        for (String line : inputList) {
            String[] split = line.split("\\s+");
            leftSide.add(Integer.parseInt(split[0]));
            rightSide.add(Integer.parseInt(split[1]));
        }
        leftSide.sort(Integer::compareTo);
        rightSide.sort(Integer::compareTo);

        int sum = 0;
        for (int i = 0; i < leftSide.size(); i++) {
            sum += Math.abs(leftSide.get(i) - rightSide.get(i));
        }
        return sum;
    }

    @Override
    public Object solvePartTwo() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();

        for (String line : inputList) {
            String[] split = line.split("\\s+");
            leftSide.add(Integer.parseInt(split[0]));
            rightSide.add(Integer.parseInt(split[1]));
        }

        long sum = 0;
        for (Integer integer : leftSide) {
            sum += integer * occurrences(rightSide, integer);
        }
        return sum;
    }

    private long occurrences(List<Integer> list, Integer element) {
        return list.stream().filter(elem -> elem.equals(element)).count();
    }
}
