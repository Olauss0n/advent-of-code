package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day07Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day07();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 3749;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 11387;
    }
}
