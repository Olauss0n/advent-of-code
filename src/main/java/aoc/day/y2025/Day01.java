package aoc.day.y2025;

import java.util.List;

import aoc.day.AdventOfCodeSolver;
import aoc.util.parse.Parser;

public class Day01 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        int dial = 50;
        int password = 0;
        List<String> inputList = Parser.toList(input);
        for (String row : inputList) {
            String rotation = row.substring(0, 1);
            int distance = Integer.parseInt(row.substring(1));

            if (rotation.equals("L")) {
                dial = (dial - distance) % 100;
            } else {
                dial = (dial + distance) % 100;
            }
            if (dial == 0) {
                password += 1;
            }
        }
        return password;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        int dial = 50;
        int password = 0;
        List<String> inputList = Parser.toList(input);
        for (String row : inputList) {
            String rotation = row.substring(0, 1);
            int distance = Integer.parseInt(row.substring(1));
            password += (distance / 100);
            distance %= 100;

            if (rotation.equals("L")) {
                if (dial > 0 && distance >= dial) {
                    password += 1;
                }
                dial = dial - distance;
            } else {
                if (dial + distance >= 100) {
                    password += 1;
                }
                dial = dial + distance;
            }
            dial = (dial + 100) % 100;
        }
        return password;
    }
}
