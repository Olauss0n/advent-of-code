package aoc.util;

public interface AdventOfCodeSolver {

    Object solvePartOne();

    Object solvePartTwo();

    default void runPartOne() {
        System.out.println("The answer for part one is: " + solvePartOne());
    }

    default void runPartTwo() {
        System.out.println("The answer for part two is: " + solvePartTwo());
    }
}
