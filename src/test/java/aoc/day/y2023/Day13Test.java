package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day13Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day13();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 405;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 400;
    }
}
