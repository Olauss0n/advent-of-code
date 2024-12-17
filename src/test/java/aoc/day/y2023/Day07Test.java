package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;

public class Day07Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day07();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 6440;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 5905;
    }
}
