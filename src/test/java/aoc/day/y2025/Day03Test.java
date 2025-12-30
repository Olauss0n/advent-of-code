package aoc.day.y2025;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day03Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day03();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 357;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 3121910778619L;
    }
}
