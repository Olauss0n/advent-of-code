package aoc.day.y2025;

import java.util.ArrayList;
import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day05 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> rangeList = Converter.convertInputToList(input.split("\n\n")[0]);
        List<Long> ingredients = Converter.convertInputToList(input.split("\n\n")[1]).stream()
                .map(Long::parseLong)
                .toList();
        List<Range> ranges = rangeList.stream()
                .map(range -> range.split("-"))
                .map(range -> new Range(Long.parseLong(range[0]), Long.parseLong(range[1])))
                .sorted()
                .toList();
        long result = 0;
        for (Long ingredient : ingredients) {
            for (Range range : ranges) {
                if (ingredient >= range.start() && ingredient <= range.stop()) {
                    result += 1;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> rangeList = Converter.convertInputToList(input.split("\n\n")[0]);
        List<Range> ranges = rangeList.stream()
                .map(range -> range.split("-"))
                .map(range -> new Range(Long.parseLong(range[0]), Long.parseLong(range[1])))
                .sorted()
                .toList();
        List<Range> mergedRanges = new ArrayList<>();
        Range current = ranges.getFirst();
        for (int i = 1; i < ranges.size(); i++) {
            Range next = ranges.get(i);
            if (next.start() <= current.stop()) {
                current = new Range(current.start(), Math.max(current.stop(), next.stop()));
            } else {
                mergedRanges.add(current);
                current = next;
            }
        }
        mergedRanges.add(current);
        return mergedRanges.stream()
                .map(range -> (range.stop() - range.start() + 1))
                .mapToLong(Long::longValue)
                .sum();
    }

    // Both inclusive
    private record Range(Long start, Long stop) implements Comparable<Range> {
        @Override
        public int compareTo(Range other) {
            if (!this.start.equals(other.start)) return this.start.compareTo(other.start);
            return this.stop.compareTo(other.stop);
        }
    }
}
