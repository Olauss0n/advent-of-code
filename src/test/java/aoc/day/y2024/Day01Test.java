package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.util.AdventOfCodeSolver;

public class Day01Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day01();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 11;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 31;
    }
}
