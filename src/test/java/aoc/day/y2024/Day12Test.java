package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day12Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day12();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 140;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 80;
    }
}
