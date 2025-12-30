package aoc.day.y2025;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day08Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day08();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 40;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 25272;
    }
}
