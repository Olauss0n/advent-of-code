package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day09Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day09();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 1928;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 2858;
    }
}
