package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day04Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day04();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 18;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 9;
    }
}
