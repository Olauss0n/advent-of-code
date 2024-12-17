package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;

public class Day11Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day11();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 374;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 8410;
    }
}
