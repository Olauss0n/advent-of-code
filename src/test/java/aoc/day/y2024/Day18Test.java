package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day18Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day18();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 22;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return "6,1";
    }
}
