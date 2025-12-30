package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day14Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day14();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 136;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 64;
    }
}
