package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day10Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day10();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 36;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 81;
    }
}
