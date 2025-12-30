package aoc.day.y2022;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day01Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day01();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 24000;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 45000;
    }
}
