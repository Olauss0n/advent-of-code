package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day06Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day06();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 41;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 6;
    }
}
