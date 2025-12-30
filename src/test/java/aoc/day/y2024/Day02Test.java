package aoc.day.y2024;

import aoc.day.AdventOfCodeBaseTest;
import aoc.day.AdventOfCodeSolver;

public class Day02Test extends AdventOfCodeBaseTest {
    @Override
    protected AdventOfCodeSolver getSolver() {
        return new Day02();
    }

    @Override
    protected Object getExampleSolutionPartOne() {
        return 2;
    }

    @Override
    protected Object getExampleSolutionPartTwo() {
        return 4;
    }
}
