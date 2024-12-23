package aoc.day.y2024;

import java.util.HashMap;

import aoc.util.AdventOfCodeSolver;

public class Day11 implements AdventOfCodeSolver {

    private final HashMap<Stone, Long> cache = new HashMap<>();

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        return commonPart(input, 25);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return commonPart(input, 75);
    }

    private Object commonPart(String input, int blinks) {
        String[] inputArray = input.replace("\n", "").split(" ");
        long answer = 0;
        for (String number : inputArray) {
            answer += blink(new Stone(Long.parseLong(number), blinks));
        }
        return answer;
    }

    private Long blink(Stone stone) {
        if (stone.blinksLeft == 0) {
            return 1L;
        }
        if (cache.get(stone) != null) {
            return cache.get(stone);
        }
        long result;
        if (stone.number == 0) {
            result = blink(new Stone(1L, stone.blinksLeft - 1));
        } else {
            int digits = (int) Math.log10(stone.number) + 1;
            if (digits % 2 == 0) {
                long divider = (long) Math.pow(10, digits / 2);
                result = blink(new Stone(stone.number / divider, stone.blinksLeft - 1))
                        + blink(new Stone(stone.number % divider, stone.blinksLeft - 1));
            } else {
                result = blink(new Stone(stone.number * 2024, stone.blinksLeft - 1));
            }
        }
        cache.put(stone, result);
        return result;
    }

    private record Stone(long number, int blinksLeft) {}
}
