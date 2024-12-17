package aoc.day.y2023;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;

public class Day06Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day06();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 288;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 71503;
    }
}
